package interpreter.expression.math;

public class MultiplyExpression extends BinaryExpression{

	public MultiplyExpression(MathExpression left, MathExpression right) {
		super(left, right);
	}

	@Override
	public double calculateNumber() {
		return this.left.calculateNumber() * this.right.calculateNumber();
	}

}
