package interpreter.expressionSolver;

import interpreter.expressionSolver.math.MathExpression;

public class NumberExpression implements MathExpression{
	private double value;
	
	public NumberExpression(double num) {
		this.value = num;
	}

	@Override
	public double calculateNumber() {
		return value;
	}
}
