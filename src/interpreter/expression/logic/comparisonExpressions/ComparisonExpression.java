package interpreter.expression.logic.comparisonExpressions;

import interpreter.expression.logic.BooleanExpression;
import interpreter.expression.math.MathExpression;

public abstract class ComparisonExpression implements BooleanExpression {
	protected MathExpression left;
	protected MathExpression right;
	
	public ComparisonExpression(MathExpression left, MathExpression right) {
		this.left = left;
		this.right = right;
	}

}
