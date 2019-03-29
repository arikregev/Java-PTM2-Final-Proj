package interpreter.expressionSolver.logic;

import interpreter.expressionSolver.math.MathExpression;

public class BiggerEqualsExpression extends ComparisonExpression {

	public BiggerEqualsExpression(MathExpression left, MathExpression right) {
		super(left, right);
	}

	@Override
	public boolean calculateLogic() {
		return left.calculateNumber() >= right.calculateNumber();
		
	}

}
