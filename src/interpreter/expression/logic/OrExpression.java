package interpreter.expression.logic;

import interpreter.symbols.Exceptions;
import interpreter.symbols.SymbolTable;
/**
 * Class that resolves an answer to the OR Expression in our new Language
 * @param left
 * @param right
 * @author Arik Regev
 * @author Amit Koren
 */
public class OrExpression extends LogicExpression {
	public OrExpression(BooleanExpression left, BooleanExpression right) {
		super(left, right);
	}
	
	/**
	 * Returns True or False for the OR Expression 
	 */
	@Override
	public boolean calculateLogic(SymbolTable symTable) throws Exceptions.SymbolException {
		return this.left.calculateLogic(symTable) || this.right.calculateLogic(symTable);
	}

}
