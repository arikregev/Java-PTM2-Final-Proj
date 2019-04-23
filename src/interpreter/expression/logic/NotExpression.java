package interpreter.expression.logic;

import interpreter.symbols.Exceptions;
import interpreter.symbols.SymbolTable;
import interpreter.symbols.Exceptions.SymbolException;

public class NotExpression implements BooleanExpression {
	private BooleanExpression param;
	
	public NotExpression(BooleanExpression param) {
		this.param = param;
	}

	@Override
	public boolean calculateLogic(SymbolTable symTable) throws Exceptions.SymbolException {
		return !param.calculateLogic(symTable);
	}

}
