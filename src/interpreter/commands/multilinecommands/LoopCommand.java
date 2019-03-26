package interpreter.commands.multilinecommands;

import interpreter.commands.Command;
import interpreter.symboles.Symbol;
import interpreter.symboles.SymbolTable;
import interpreter.symboles.SymbolTable.SymbolException;

public class LoopCommand implements Command {
	private String varName;
	private String[] parameters;
	
	public LoopCommand(String varName, String[] parms) {
		this.varName = varName;
		this.parameters = parms;
	}
	@Override
	public void doCommand(SymbolTable symTable) throws SymbolException {
		Symbol sym = symTable.getSymbol(this.varName);
		// TODO: complete after solve Expression.

	}

}
