package interpreter.expression.math;

public class DivideExpression extends BinaryExpression {

	public DivideExpression(MathExpression left, MathExpression right) {
		super(left, right);
	}

	@Override
	public double calculateNumber() {
		return this.left.calculateNumber() / this.right.calculateNumber();
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof DivideExpression))
			return false;
		DivideExpression other = (DivideExpression) obj;
		return left.equals(other.left) && right.equals(other.right);
	}
}
