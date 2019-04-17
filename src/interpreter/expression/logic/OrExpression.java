package interpreter.expression.logic;

import interpreter.symboles.SymbolTable;
import interpreter.symboles.SymbolTable.SymbolException;

public class OrExpression extends LogicExpression {
	/**
	 * Class to resolve an answer to the OR Expression in out new Language
	 * @param left
	 * @param right
	 * @author Arik Regev
	 * @author Amit Koren
	 */
	public OrExpression(BooleanExpression left, BooleanExpression right) {
		super(left, right);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Returns True or False for the OR Expression 
	 */
	@Override
	public boolean calculateLogic(SymbolTable symTable) throws SymbolException {
		// TODO Auto-generated method stub
		return this.left.calculateLogic(symTable) || this.right.calculateLogic(symTable);
	}

}
