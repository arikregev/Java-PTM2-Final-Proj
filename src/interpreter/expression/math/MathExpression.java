package interpreter.expression.math;

import interpreter.symboles.SymbolTable;
import interpreter.symboles.SymbolTable.SymbolException;
import interpreter.symboles.SymbolTable.SymbolNotExistException;

public interface MathExpression {
	public double calculateNumber(SymbolTable symTable) throws SymbolException;
}
