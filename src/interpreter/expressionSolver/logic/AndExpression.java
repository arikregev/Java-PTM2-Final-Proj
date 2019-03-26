package interpreter.expressionSolver.logic;

public class AndExpression extends LogicExpression {

	/**
	 * Class to resolve an answer to the AND Expression in out new Language
	 * @param left
	 * @param right
	 */
	public AndExpression(LogicExpression left, LogicExpression right) {
		super(left, right);	
	}
	
	/**
	 * Returns True or False for the AND Expression 
	 */
	@Override
	public boolean calculateLogic() {
		return left.calculateLogic() && right.calculateLogic();
	}

}
