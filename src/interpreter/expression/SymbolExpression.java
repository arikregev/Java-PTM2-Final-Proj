package interpreter.expression;

import interpreter.expression.logic.BooleanExpression;
import interpreter.expression.math.MathExpression;
import interpreter.symboles.Symbol;

public class SymbolExpression implements MathExpression, BooleanExpression {
	private Symbol sym;
	
	public SymbolExpression(Symbol sym) {
		this.sym = sym;
	}
	@Override
	public double calculateNumber() {
		return sym.getValue();
	}
	@Override
	public boolean calculateLogic() {
		return sym.getValue()!=0;
	}
	public Symbol getSym() {
		return sym;
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
		return sym.equals(other.sym);
	}
	
}
