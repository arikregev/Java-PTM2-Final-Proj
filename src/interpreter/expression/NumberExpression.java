package interpreter.expression;

import interpreter.expression.logic.BooleanExpression;
import interpreter.expression.math.MathExpression;

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
