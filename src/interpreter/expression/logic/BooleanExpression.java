package interpreter.expression.logic;

import interpreter.symboles.SymbolTable;
import interpreter.symboles.SymbolTable.SymbolException;
import interpreter.symboles.SymbolTable.SymbolNotExistException;

public interface BooleanExpression {
	public boolean calculateLogic(SymbolTable symTable) throws SymbolException;
}
