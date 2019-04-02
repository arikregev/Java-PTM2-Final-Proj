package interpreter.expression.math;

public class PlusExpression extends BinaryExpression {

	public PlusExpression(MathExpression left, MathExpression right) {
		super(left, right);
	}

	@Override
	public double calculateNumber() {
		return this.left.calculateNumber() + this.right.calculateNumber();
	}

}
