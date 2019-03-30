package interpreter.commands.factory;

import interpreter.commands.Command;

public class CommandFactory {
	private interface CommandCreator{
		public Command create();
	}

}
