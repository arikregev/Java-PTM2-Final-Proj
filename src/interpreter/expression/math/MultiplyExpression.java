package interpreter.expression.math;

import interpreter.symbols.Exceptions;
import interpreter.symbols.SymbolTable;

public class MultiplyExpression extends BinaryExpression{

	public MultiplyExpression(MathExpression left, MathExpression right) {
		super(left, right);
	}

	@Override
	public double calculateNumber(SymbolTable symTable) throws Exceptions.SymbolException {
		return this.left.calculateNumber(symTable) * this.right.calculateNumber(symTable);
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
