package interpreter.commands.singlecommands;

import interpreter.commands.Command;
import interpreter.symboles.Symbol;
import interpreter.symboles.SymbolTable;
import interpreter.symboles.SymbolTable.SymbolException;
/**
 * The Command was created in the purpose of 
 * having the ability to assign values to our variables in
 * our newly invented Language.
 * @author Arik Regev
 * @author Amit Koren
 */
public class AssignCommand implements Command {
	private String varName;
	private String[] parameters;
	
	public AssignCommand(String varName, String[] parms) {
		this.varName = varName;
		this.parameters = parms;
	}
	
	@Override
	public void doCommand(SymbolTable symTable) throws SymbolException {
		Symbol sym = symTable.getSymbol(this.varName);
		// TODO: complete after solve Expression.

	}

}
