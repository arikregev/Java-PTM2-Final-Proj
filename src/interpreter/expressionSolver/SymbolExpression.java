package interpreter.expressionSolver;

import interpreter.expressionSolver.logic.BooleanExpression;
import interpreter.expressionSolver.math.MathExpression;
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
	
}
