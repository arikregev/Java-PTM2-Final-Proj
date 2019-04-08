package interpreter.expression.math;

public class MultiplyExpression extends BinaryExpression{

	public MultiplyExpression(MathExpression left, MathExpression right) {
		super(left, right);
	}

	@Override
	public double calculateNumber() {
		return this.left.calculateNumber() * this.right.calculateNumber();
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof MultiplyExpression))
			return false;
		MultiplyExpression other = (MultiplyExpression) obj;
		return left.equals(other.left) && right.equals(other.right);
	}
}
