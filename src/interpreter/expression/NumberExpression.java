package interpreter.expression;

import interpreter.expression.logic.BooleanExpression;
import interpreter.expression.math.MathExpression;
import interpreter.symbols.Exceptions;
import interpreter.symbols.SymbolTable;
import interpreter.symbols.Exceptions.SymbolException;

public class NumberExpression implements MathExpression, BooleanExpression{
	private double value;
	
	public NumberExpression(double num) {
		this.value = num;
	}

	@Override
	public double calculateNumber(SymbolTable symTable) throws Exceptions.SymbolException {
		return value;
	}

	@Override
	public boolean calculateLogic(SymbolTable symTable) throws Exceptions.SymbolException {
		return value !=0;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof NumberExpression))
			return false;
		NumberExpression other = (NumberExpression) obj;
		return Double.doubleToLongBits(value) == Double.doubleToLongBits(other.value);
	}
	
	
}
