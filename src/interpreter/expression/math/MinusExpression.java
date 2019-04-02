package interpreter.expression.math;

public class MinusExpression extends BinaryExpression {

	public MinusExpression(MathExpression left, MathExpression right) {
		super(left, right);
	}

	@Override
	public double calculateNumber() {
		return this.left.calculateNumber() - this.right.calculateNumber();
	}

}
