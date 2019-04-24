package test;

import java.io.EOFException;

import interpreter.Interpreter;

public class ArikMain {

	public static void main(String[] args) {
		String[] paths = {
				"/instrumentation/airspeed-indicator/indicated-speed-kt",
				"/instrumentation/altimeter/indicated-altitude-ft", 
				"/instrumentation/altimeter/pressure-alt-ft",
				"/instrumentation/attitude-indicator/indicated-pitch-deg",
				"/instrumentation/attitude-indicator/indicated-roll-deg",
				"/instrumentation/attitude-indicator/internal-pitch-deg",
				"/instrumentation/attitude-indicator/internal-roll-deg",
				"/instrumentation/encoder/indicated-altitude-ft",
				"/instrumentation/encoder/pressure-alt-ft",
				"/instrumentation/gps/indicated-altitude-ft",
				"/instrumentation/gps/indicated-ground-speed-kt",
				"/instrumentation/gps/indicated-vertical-speed",
				"/instrumentation/heading-indicator/indicated-heading-deg",
				"/instrumentation/magnetic-compass/indicated-heading-deg",
				"/instrumentation/slip-skid-ball/indicated-slip-skid",
				"/instrumentation/turn-indicator/indicated-turn-rate",
				"/instrumentation/vertical-speed-indicator/indicated-speed-fpm",
				"/controls/flight/aileron",
				"/controls/flight/elevator",
				"/controls/flight/flaps",
				"/controls/engines/engine/throttle",
				"/engines/engine/rpm",
				"/controls/flight/speedbrake",
				"/controls/switches/master-bat" 
				};
		new Interpreter(paths) {
			String[] s = {"openDataServer 5400 10", 
					"connect 127.0.0.1 5402",
					"var breaks = bind /controls/flight/speedbrake",
					"var throttle = bind controls/engines/current-engine/throttle",
					"var heading = bind /instrumentation/heading-indicator/offset-deg",
					"var airspeed = bind /instrumentation/airspeed-indicator/indicated-speed-kt",
					"var roll = bind /instrumentation/attitude-indicator/indicated-roll-deg",
					"var pitch = bind /instrumentation/attitude-indicator/internal-pitch-deg",
					"var rudder = bind /controls/flight/rudder",
					"var aileron = bind /controls/flight/aileron",
					"var elevator = bind /controls/flight/elevator",
					"var alt = bind /instrumentation/altimeter/indicated-altitude-ft",
					"breaks = 0",
					"throttle = 1",
					"var h0 = heading",
					"while alt < 1000 {",
					"	rudder = (h0 – heading)/20",
					"}"};
			int ii = 0;
			@Override
			public String getLine() throws EOFException {
				if (ii >= s.length)
					throw new EOFException();
				return s[ii++];
			}
		}.run();
	}

}
