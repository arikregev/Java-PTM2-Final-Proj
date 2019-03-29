package interpreter.expressionSolver.logic;

public class NotExpression implements BooleanExpression {
	private BooleanExpression param;
	
	public NotExpression(BooleanExpression param) {
		this.param = param;
	}

	@Override
	public boolean calculateLogic() {
		return !param.calculateLogic();
	}

}
