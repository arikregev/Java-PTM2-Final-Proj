package interpreter.expression.logic.comparisonExpressions;

import interpreter.expression.math.MathExpression;
import interpreter.symboles.SymbolTable;
import interpreter.symboles.SymbolTable.SymbolException;

public class NotEqualsExpression extends ComparisonExpression {

	public NotEqualsExpression(MathExpression left, MathExpression right) {
		super(left, right);
	}

	@Override
	public boolean calculateLogic(SymbolTable symTable) throws SymbolException {
		return (left.calculateNumber(symTable) - right.calculateNumber(symTable)) > 1e-3;
		
	}

}
