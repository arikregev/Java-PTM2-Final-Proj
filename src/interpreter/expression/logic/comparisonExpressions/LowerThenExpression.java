package interpreter.expression.logic.comparisonExpressions;


import interpreter.expression.math.MathExpression;

public class LowerThenExpression extends ComparisonExpression {

	public LowerThenExpression(MathExpression left, MathExpression right) {
		super(left, right);
	}

	@Override
	public boolean calculateLogic() {
		return left.calculateNumber() < right.calculateNumber();
		
	}
	
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof LowerThenExpression))
			return false;
		LowerThenExpression other = (LowerThenExpression) obj;
		return left.equals(other.left) && right.equals(other.right);
	}
}
