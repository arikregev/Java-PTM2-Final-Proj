package test;

import java.io.EOFException;

import interpreter.Interpreter;

public class MyInterpreter {

	public static int interpret(String[] lines){
		Interpreter ipter = new Interpreter() {
			int i = 0;
			@Override
			public String getLine() throws EOFException {
				if (i >= lines.length)
					throw new EOFException();
				return lines[i++];
			}
			
		};
		ipter.run();
		return (int) ipter.getReturnValue();
	}
}
