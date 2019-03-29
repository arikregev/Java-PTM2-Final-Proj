package interpreter.expressionSolver.logic.comparisonExpressions;

import interpreter.expressionSolver.math.MathExpression;

public class EqualsExpression extends ComparisonExpression {

	public EqualsExpression(MathExpression left, MathExpression right) {
		super(left, right);
	}

	@Override
	public boolean calculateLogic() {
		return (left.calculateNumber() - right.calculateNumber()) <= 1e-3;
	}

}
