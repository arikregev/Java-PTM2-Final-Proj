package interpreter.commands.servercommands;

import interpreter.commands.Command;
import interpreter.symboles.Symbol;
import interpreter.symboles.SymbolTable;
import interpreter.symboles.SymbolTable.SymbolException;
/**
 * The Command was created in the purpose of 
 * having the ability to connect to the FlightGear Server
 * @author Arik Regev
 * 
 */
public class ConnectCommand implements Command {
	private String varName;
	private String[] parameters;
	
	public ConnectCommand(String varName, String[] parms) {
		this.varName = varName;
		this.parameters = parms;
	}
	@Override
	public void doCommand(SymbolTable symTable) throws SymbolException {
		Symbol sym = symTable.getSymbol(this.varName);
		// TODO: complete after solve Expression.

	}

}
