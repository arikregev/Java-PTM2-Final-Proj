package interpreter.commands.servercommands;

import java.util.List;

import interpreter.Interpreter.ParseException;
import interpreter.commands.Command;
import interpreter.commands.factory.CommandFactory;
import interpreter.symboles.Symbol;
import interpreter.symboles.SymbolTable;
import interpreter.symboles.SymbolTable.SymbolException;
/**
 * The Command was created in the purpose of 
 * having the ability to connect to the FlightGear Server
 * @author Arik Regev
 * @author Amit Koren
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
