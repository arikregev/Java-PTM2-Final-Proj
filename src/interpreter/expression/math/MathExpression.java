package interpreter.expression.math;

import interpreter.symbols.Exceptions;
import interpreter.symbols.SymbolTable;
import interpreter.symbols.Exceptions.SymbolException;
import interpreter.symbols.Exceptions.SymbolNotExistException;

public interface MathExpression {
	public double calculateNumber(SymbolTable symTable) throws Exceptions.SymbolException;
}
