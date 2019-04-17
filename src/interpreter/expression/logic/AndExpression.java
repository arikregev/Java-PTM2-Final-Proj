package interpreter.expression.logic;

import interpreter.symboles.SymbolTable;
import interpreter.symboles.SymbolTable.SymbolException;

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
	public boolean calculateLogic(SymbolTable symTable) throws SymbolException {
		return left.calculateLogic(symTable) && right.calculateLogic(symTable); //Returns True or False for an AND Expression
	}

}
