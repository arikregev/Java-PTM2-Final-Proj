package interpreter.commands;

import interpreter.symbols.SymbolTable;
/**
 * This interface is being Inherited by every Command
 * that will be able to use in our newly invented Language.
 * @author Arik Regev
 * @author Amit Koren
 */
public interface Command {
	
	public boolean execute(SymbolTable symTable) throws ExecutionException; 
	
}
