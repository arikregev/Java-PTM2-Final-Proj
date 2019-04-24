package interpreter.commands.multilinecommands;

import java.util.List;

import interpreter.Interpreter.ParseException;
import interpreter.commands.Command;
import interpreter.commands.ExecutionException;
import interpreter.commands.factory.CommandFactory;
import interpreter.expression.builders.ExpressionBuilder;
import interpreter.expression.logic.BooleanExpression;
import interpreter.symbols.SymbolTable;

/**
 * Defining how our While Command will behave 
 * 
 * @author Arik Regev
 * @author Amit Koren
 */
public class WhileCommand extends ControlCommand{
	
	private BooleanExpression exp;
	
	public WhileCommand(BooleanExpression exp) {
		this.exp = exp;
	}
	@Override
	public boolean execute(SymbolTable symTable) throws ExecutionException {
		while(exp.calculateLogic(symTable)) {
			if(!innerCommand.execute(symTable))
				return false;
		}
		return true;
	}
	public static class Factory extends CommandFactory{
		@Override
		public Command create(List<String> tokens) throws ParseException {
			BooleanExpression booleanExpr = new ExpressionBuilder().createBooleanExpression(tokens);
			if (!tokens.isEmpty())
				throw new ParseException("Invalid expression at: " + tokens.get(0));
			return new WhileCommand(booleanExpr);
		}
		
	}

}
