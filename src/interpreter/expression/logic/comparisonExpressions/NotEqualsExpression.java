package interpreter.expression.logic.comparisonExpressions;

import interpreter.expression.math.MathExpression;

public class NotEqualsExpression extends ComparisonExpression {

	public NotEqualsExpression(MathExpression left, MathExpression right) {
		super(left, right);
	}

	@Override
	public boolean calculateLogic() {
		return (left.calculateNumber() - right.calculateNumber()) > 1e-3;
		
	}

}
