package interpreter.commands.singlecommands;

import java.util.List;

import interpreter.Interpreter.ParseException;
import interpreter.commands.Command;
import interpreter.commands.factory.CommandFactory;
import interpreter.expression.builders.ExpressionBuilder;
import interpreter.expression.math.MathExpression;
import interpreter.symboles.SymbolTable;
import interpreter.symboles.SymbolTable.SymbolException;

public class ReturnCommand implements Command{
	
	private MathExpression exp;
	
	public ReturnCommand(MathExpression e) {
		this.exp = e;
	}
	@Override
	public boolean execute(SymbolTable symTable) throws SymbolException {
		symTable.setReturnValue(this.exp.calculateNumber(symTable));
		return false;
	}
	
	public static class Factory extends CommandFactory{
		@Override
		public Command create(List<String> tokens) throws ParseException, SymbolException {
			MathExpression exp = new ExpressionBuilder().createMathExpression(tokens);
			if (!tokens.isEmpty())
				throw new ParseException("Invalid expression at: " + tokens.get(0));
			return new ReturnCommand(exp);
		}
		
	}


}
