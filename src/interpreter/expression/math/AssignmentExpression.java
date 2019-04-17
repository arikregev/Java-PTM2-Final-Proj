package interpreter.expression.math;

import interpreter.expression.SymbolExpression;
import interpreter.symboles.SymbolTable;
import interpreter.symboles.SymbolTable.SymbolException;

public class AssignmentExpression implements MathExpression {
	private SymbolExpression left;
	private MathExpression right;

	public AssignmentExpression(SymbolExpression left, MathExpression right) {
		this.left = left;
		this.right = right;
	}

	@Override
	public double calculateNumber(SymbolTable symTable) throws SymbolException {
		double value = right.calculateNumber(symTable);
		left.getSym(symTable).setValue(value);
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
