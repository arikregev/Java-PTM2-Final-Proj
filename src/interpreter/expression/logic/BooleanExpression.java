package interpreter.expression.logic;

import interpreter.symbols.Exceptions;
import interpreter.symbols.SymbolTable;

public interface BooleanExpression {
	public boolean calculateLogic(SymbolTable symTable) throws Exceptions.SymbolException;
}
