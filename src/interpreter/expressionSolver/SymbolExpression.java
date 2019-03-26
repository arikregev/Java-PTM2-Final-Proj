package interpreter.expressionSolver;

import interpreter.expressionSolver.math.MathExpression;
import interpreter.symboles.Symbol;

public class SymbolExpression implements MathExpression {
	private Symbol sym;
	
	public SymbolExpression(Symbol sym) {
		this.sym = sym;
	}
	@Override
	public double calculateNumber() {
		return sym.getValue();
	}
	
}
