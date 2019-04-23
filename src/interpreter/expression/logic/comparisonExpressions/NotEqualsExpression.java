package interpreter.expression.logic.comparisonExpressions;

import interpreter.expression.math.MathExpression;
import interpreter.symbols.Exceptions;
import interpreter.symbols.SymbolTable;
import interpreter.symbols.Exceptions.SymbolException;

public class NotEqualsExpression extends ComparisonExpression {

	public NotEqualsExpression(MathExpression left, MathExpression right) {
		super(left, right);
	}

	@Override
	public boolean calculateLogic(SymbolTable symTable) throws Exceptions.SymbolException {
		return (left.calculateNumber(symTable) - right.calculateNumber(symTable)) > 1e-3;
		
	}

}
