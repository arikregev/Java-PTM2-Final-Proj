package interpreter.commands.servercommands;

import java.io.IOException;
import interpreter.commands.ExecutionException;

@SuppressWarnings("serial")
public class IOExceptionWrapper extends ExecutionException {
	public final IOException e;

	public IOExceptionWrapper(IOException e) {
		super();
		this.e = e;
	}
	
	
}
