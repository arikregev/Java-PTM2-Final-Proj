package interpreter.expressionSolver.logic.comparisonExpressions;

import interpreter.expressionSolver.logic.BooleanExpression;
import interpreter.expressionSolver.math.MathExpression;

public abstract class ComparisonExpression implements BooleanExpression {
	protected MathExpression left;
	protected MathExpression right;
	
	public ComparisonExpression(MathExpression left, MathExpression right) {
		this.left = left;
		this.right = right;
	}

}
