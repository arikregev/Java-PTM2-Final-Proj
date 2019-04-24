package interpreter.expression.math;

import interpreter.symbols.Exceptions;
import interpreter.symbols.SymbolTable;

public class UnaryMinusExpression implements MathExpression {
	private MathExpression exp;
	
	public UnaryMinusExpression(MathExpression exp) {
		this.exp = exp;
	}

	@Override
	public double calculateNumber(SymbolTable symTable) throws Exceptions.SymbolException {
		return -(this.exp.calculateNumber(symTable));
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof UnaryMinusExpression))
			return false;
		UnaryMinusExpression other = (UnaryMinusExpression) obj;
		return exp.equals(other.exp);
	}
}
