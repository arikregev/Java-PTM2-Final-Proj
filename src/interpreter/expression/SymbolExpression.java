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
	
}
