package interpreter.expression.math;

import interpreter.symbols.Exceptions;
import interpreter.symbols.SymbolTable;
import interpreter.symbols.Exceptions.SymbolException;

public class PlusExpression extends BinaryExpression {

	public PlusExpression(MathExpression left, MathExpression right) {
		super(left, right);
	}

	@Override
	public double calculateNumber(SymbolTable symTable) throws Exceptions.SymbolException {
		return this.left.calculateNumber(symTable) + this.right.calculateNumber(symTable);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof PlusExpression))
			return false;
		PlusExpression other = (PlusExpression) obj;
		return left.equals(other.left) && right.equals(other.right);
	}
}
