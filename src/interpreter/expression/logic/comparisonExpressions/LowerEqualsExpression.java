package interpreter.expression.logic.comparisonExpressions;

import interpreter.expression.math.MathExpression;

public class LowerEqualsExpression extends ComparisonExpression {

	public LowerEqualsExpression(MathExpression left, MathExpression right) {
		super(left, right);
	}

	@Override
	public boolean calculateLogic() {
		return left.calculateNumber() <= right.calculateNumber();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof LowerEqualsExpression))
			return false;
		LowerEqualsExpression other = (LowerEqualsExpression) obj;
		return left.equals(other.left) && right.equals(other.right);
	}

}
