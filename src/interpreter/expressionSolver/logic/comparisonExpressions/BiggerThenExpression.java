package interpreter.expressionSolver.logic.comparisonExpressions;

import interpreter.expressionSolver.math.MathExpression;

public class BiggerThenExpression extends ComparisonExpression {

	public BiggerThenExpression(MathExpression left, MathExpression right) {
		super(left, right);
	}

	@Override
	public boolean calculateLogic() {
		return left.calculateNumber() > right.calculateNumber(); //Returns True or False for an Bigger Then Expression
		
	}

}
