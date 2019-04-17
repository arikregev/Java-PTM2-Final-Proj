package interpreter.expression.logic;

import interpreter.symboles.SymbolTable;
import interpreter.symboles.SymbolTable.SymbolException;

public class NotExpression implements BooleanExpression {
	private BooleanExpression param;
	
	public NotExpression(BooleanExpression param) {
		this.param = param;
	}

	@Override
	public boolean calculateLogic(SymbolTable symTable) throws SymbolException {
		return !param.calculateLogic(symTable);
	}

}
