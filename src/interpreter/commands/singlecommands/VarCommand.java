package interpreter.commands.singlecommands;

import interpreter.commands.Command;
import interpreter.expressionSolver.math.MathExpression;
import interpreter.symboles.BindSymbol;
import interpreter.symboles.RegularSymbol;
import interpreter.symboles.Symbol;
import interpreter.symboles.SymbolTable;
import interpreter.symboles.SymbolTable.SymbolException;
/**
 * Every Var Command represents a link to a parameter in the aircraft.<br>
 * TODO ...
 * @param String SymbolName
 * @param SymbolFactory
 * @author Arik Regev
 */
public class VarCommand implements Command {
	/**
	 * Symbol Factory is a static interface for the purpose of creating new symbols<br>
	 * using Lambda Expressions and inserting the newly created symbol into the Symbol Table.
	 * @author Arik Regev
	 */
	private static interface SymbolFactory{
		public Symbol createSymbol(SymbolTable symTable);
	}
	
	public String symName;
	public SymbolFactory s;
	
	public VarCommand(String symName, MathExpression exp) {
		this.symName = symName;
		this.s = (symTable)->{return new RegularSymbol(exp.calculateNumber());};
	}
		
	public VarCommand(String symName, String path) {
		this.symName = symName;
		this.s = (symTable)->{return new BindSymbol(path, symTable);};
		
	}

	@Override
	public void doCommand(SymbolTable symTable) throws SymbolException {
		symTable.addSymbol(symName, s.createSymbol(symTable));
	}

}
