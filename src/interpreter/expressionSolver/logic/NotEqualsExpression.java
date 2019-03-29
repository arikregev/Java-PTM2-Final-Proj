package interpreter.expressionSolver.logic;

import interpreter.expressionSolver.math.MathExpression;

public class NotEqualsExpression extends ComparisonExpression {

	public NotEqualsExpression(MathExpression left, MathExpression right) {
		super(left, right);
	}

	@Override
	public boolean calculateLogic() {
		return (left.calculateNumber() - right.calculateNumber()) > 1e-3;
		
	}

}
