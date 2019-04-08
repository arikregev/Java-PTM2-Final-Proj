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

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof AssignmentExpression))
			return false;
		AssignmentExpression other = (AssignmentExpression) obj;
		return left.equals(other.left) && right.equals(other.right);
	}

}
