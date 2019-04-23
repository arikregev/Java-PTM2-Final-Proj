package interpreter.expression.logic;

import interpreter.symbols.Exceptions;
import interpreter.symbols.SymbolTable;
import interpreter.symbols.Exceptions.SymbolException;
import interpreter.symbols.Exceptions.SymbolNotExistException;

public interface BooleanExpression {
	public boolean calculateLogic(SymbolTable symTable) throws Exceptions.SymbolException;
}
