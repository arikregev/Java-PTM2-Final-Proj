package interpreter.expression.logic;

/**
 * The purpose of this class to resolve an answer to the AND Expression in out new Language
 * @param left
 * @param right
 * @author Arik Regev
 * @author Amit Koren
 */
public class AndExpression extends LogicExpression {

	public AndExpression(BooleanExpression left, BooleanExpression right) {
		super(left, right);	
	}
	
	@Override
	public boolean calculateLogic() {
		return left.calculateLogic() && right.calculateLogic(); //Returns True or False for an AND Expression
	}

}
