package interpreter.commands.multilinecommands;

import interpreter.commands.Command;

public abstract class ControlCommand implements Command {
	protected Command innerCommand;
	
	public void addInner(Command c) {
	this.innerCommand = c;	
	}
}
