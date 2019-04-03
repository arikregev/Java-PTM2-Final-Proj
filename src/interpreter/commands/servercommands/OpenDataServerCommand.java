package interpreter.commands.servercommands;

import java.util.List;

import interpreter.Interpreter.ParseException;
import interpreter.commands.Command;
import interpreter.commands.factory.CommandFactory;
import interpreter.symboles.SymbolTable;
import interpreter.symboles.SymbolTable.SymbolException;

public class OpenDataServerCommand implements Command {
	private int port;
	private double rate;
	
	public OpenDataServerCommand(String[] parameters) {
		
	}
	@Override
	public void doCommand(SymbolTable symTable) throws SymbolException {
		// TODO Auto-generated method stub

	}
	public static class Factory extends CommandFactory{
		public Factory(SymbolTable symTable) {
			super(symTable);
		}
		@Override
		public Command create(List<String> tokens) throws ParseException, SymbolException {
			// TODO Auto-generated method stub
			return null;
		}
		
	}

}
