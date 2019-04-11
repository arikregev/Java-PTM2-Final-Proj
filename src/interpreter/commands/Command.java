package interpreter.commands;

import interpreter.symboles.SymbolTable;
import interpreter.symboles.SymbolTable.SymbolException;
/**
 * This interface is being Inherited by every Command
 * that will be able to use in our newly invented Language.
 * @author Arik Regev
 * @author Amit Koren
 */
public interface Command {
	
	public void execute(SymbolTable symTable) throws SymbolException; 
	
}
