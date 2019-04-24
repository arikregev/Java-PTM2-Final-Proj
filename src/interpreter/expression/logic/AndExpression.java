package interpreter.expression.logic;

import interpreter.symbols.Exceptions;
import interpreter.symbols.SymbolTable;

/**
 * The purpose of this class to resolve an answer to the AND Expression in out new Language
 * @param left
 * @param right
 * @author Arik Regev
 * @author Amit Koren
 */
public class AndExpression extends LogicExpression {

	public AndExpression(BooleanExpression left, BooleanExpression right) {
		super(left, right);	
	}
	
	@Override
	public boolean calculateLogic(SymbolTable symTable) throws Exceptions.SymbolException {
		return left.calculateLogic(symTable) && right.calculateLogic(symTable); //Returns True or False for an AND Expression
	}

}
