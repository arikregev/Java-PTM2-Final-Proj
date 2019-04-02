package interpreter.expression.math;

public class UnaryMinusExpression implements MathExpression {
	private MathExpression exp;
	
	public UnaryMinusExpression(MathExpression exp) {
		this.exp = exp;
	}

	@Override
	public double calculateNumber() {
		return -(this.exp.calculateNumber());
	}

}
