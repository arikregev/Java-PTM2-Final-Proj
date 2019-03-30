package interpreter.commands.servercommands;

import interpreter.commands.Command;
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

}
