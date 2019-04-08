package interpreter.expression.logic.comparisonExpressions;

import interpreter.expression.math.MathExpression;

public class BiggerEqualsExpression extends ComparisonExpression {

	public BiggerEqualsExpression(MathExpression left, MathExpression right) {
		super(left, right);
	}

	@Override
	public boolean calculateLogic() {
		return left.calculateNumber() >= right.calculateNumber(); //Returns True or False for an BiggerEquals Then Expression
	}
	
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof BiggerEqualsExpression))
			return false;
		BiggerEqualsExpression other = (BiggerEqualsExpression) obj;
		return left.equals(other.left) && right.equals(other.right);
	}

}
