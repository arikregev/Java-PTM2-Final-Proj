package interpreter.expressionSolver;

import interpreter.expressionSolver.logic.BooleanExpression;
import interpreter.expressionSolver.math.MathExpression;

public class NumberExpression implements MathExpression, BooleanExpression{
	private double value;
	
	public NumberExpression(double num) {
		this.value = num;
	}

	@Override
	public double calculateNumber() {
		return value;
	}

	@Override
	public boolean calculateLogic() {
		return value !=0;
	}
}
