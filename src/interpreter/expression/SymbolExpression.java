package interpreter.expression;

import interpreter.expression.logic.BooleanExpression;
import interpreter.expression.math.MathExpression;
import interpreter.symboles.Symbol;
import interpreter.symboles.SymbolTable;
import interpreter.symboles.SymbolTable.SymbolException;
import interpreter.symboles.SymbolTable.SymbolNotExistException;

public class SymbolExpression implements MathExpression, BooleanExpression {
	String symbol;
	
	public SymbolExpression(String symbol) {
		this.symbol = symbol;
	}
	@Override
	public double calculateNumber(SymbolTable symTable) throws SymbolNotExistException, SymbolException {
		return getSym(symTable).getValue();
	}
	@Override
	public boolean calculateLogic(SymbolTable symTable) throws SymbolNotExistException, SymbolException {
		return getSym(symTable).getValue()!=0;
	}
	public Symbol getSym(SymbolTable symTable) throws SymbolNotExistException {
		return symTable.getSymbol(symbol);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof SymbolExpression))
			return false;
		SymbolExpression other = (SymbolExpression) obj;
//		if (sym == null && other.sym != null) {
//			return false;
//		} 
		return symbol.equals(other.symbol);
	}
	
}
