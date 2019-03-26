package interpreter.commands.multilinecommands;

import interpreter.symboles.Symbol;
import interpreter.symboles.SymbolTable;
import interpreter.symboles.SymbolTable.SymbolException;

public class IfCommand extends MultiLineCommand {
	private String varName;
	private String[] parameters;
	
	public IfCommand(String varName, String[] parms) {
		this.varName = varName;
		this.parameters = parms;
	}
	
	@Override
	public void doCommand(SymbolTable symTable) throws SymbolException {
		Symbol sym = symTable.getSymbol(this.varName);
		// TODO: complete after solve Expression.

	}

}
