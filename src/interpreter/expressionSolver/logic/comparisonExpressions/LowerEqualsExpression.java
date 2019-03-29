package interpreter.expressionSolver.logic.comparisonExpressions;

import interpreter.expressionSolver.math.MathExpression;

public class LowerEqualsExpression extends ComparisonExpression {

	public LowerEqualsExpression(MathExpression left, MathExpression right) {
		super(left, right);
	}

	@Override
	public boolean calculateLogic() {
		return left.calculateNumber() <= right.calculateNumber();
		
	}

}
