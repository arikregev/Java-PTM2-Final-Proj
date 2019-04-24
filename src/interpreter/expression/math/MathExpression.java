package interpreter.expression.math;

import interpreter.symbols.Exceptions;
import interpreter.symbols.SymbolTable;

public interface MathExpression {
	public double calculateNumber(SymbolTable symTable) throws Exceptions.SymbolException;
}
