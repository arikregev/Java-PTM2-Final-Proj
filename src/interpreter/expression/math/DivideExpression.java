package interpreter.expression.math;

public class DivideExpression extends BinaryExpression {

	public DivideExpression(MathExpression left, MathExpression right) {
		super(left, right);
	}

	@Override
	public double calculateNumber() {
		return this.left.calculateNumber() / this.right.calculateNumber();
	}

}
