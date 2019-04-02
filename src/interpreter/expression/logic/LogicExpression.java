package interpreter.expression.logic;

public abstract class LogicExpression implements BooleanExpression {
	protected BooleanExpression left;
	protected BooleanExpression right;
	
	public LogicExpression(BooleanExpression left, BooleanExpression right) {
		this.left = left;
		this.right = right;
	}
}
