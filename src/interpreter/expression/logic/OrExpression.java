package interpreter.expression.logic;

public class OrExpression extends LogicExpression {
	/**
	 * Class to resolve an answer to the OR Expression in out new Language
	 * @param left
	 * @param right
	 * @author Arik Regev
	 * @author Amit Koren
	 */
	public OrExpression(LogicExpression left, LogicExpression right) {
		super(left, right);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Returns True or False for the OR Expression 
	 */
	@Override
	public boolean calculateLogic() {
		// TODO Auto-generated method stub
		return this.left.calculateLogic() || this.right.calculateLogic();
	}

}
