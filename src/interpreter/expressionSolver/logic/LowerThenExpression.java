package interpreter.expressionSolver.logic;

import interpreter.expressionSolver.math.MathExpression;

public class LowerThenExpression extends ComparisonExpression {

	public LowerThenExpression(MathExpression left, MathExpression right) {
		super(left, right);
	}

	@Override
	public boolean calculateLogic() {
		return left.calculateNumber() < right.calculateNumber();
		
	}

}
