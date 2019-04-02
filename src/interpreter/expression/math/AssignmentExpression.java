package interpreter.expression.math;

import interpreter.expression.SymbolExpression;

public class AssignmentExpression implements MathExpression {
	private SymbolExpression left;
	private MathExpression right;

	public AssignmentExpression(SymbolExpression left, MathExpression right) {
		this.left = left;
		this.right = right;
	}

	@Override
	public double calculateNumber() {
		double value = right.calculateNumber();
		left.getSym().setValue(value);
		return value;
	}

}
