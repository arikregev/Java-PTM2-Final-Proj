package interpreter.expression.logic.comparisonExpressions;

import interpreter.expression.math.MathExpression;
import interpreter.symbols.Exceptions;
import interpreter.symbols.SymbolTable;

public class EqualsExpression extends ComparisonExpression {

	public EqualsExpression(MathExpression left, MathExpression right) {
		super(left, right);
	}

	@Override
	public boolean calculateLogic(SymbolTable symTable) throws Exceptions.SymbolException {
		return (left.calculateNumber(symTable) - right.calculateNumber(symTable)) <= 1e-3;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof EqualsExpression))
			return false;
		EqualsExpression other = (EqualsExpression) obj;
		return left.equals(other.left) && right.equals(other.right);
	}

}
