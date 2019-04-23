package interpreter.commands.singlecommands;

import java.util.List;
import java.util.concurrent.TimeUnit;

import interpreter.Interpreter.ParseException;
import interpreter.commands.Command;
import interpreter.commands.ExecutionException;
import interpreter.commands.factory.CommandFactory;
import interpreter.expression.builders.ExpressionBuilder;
import interpreter.expression.math.MathExpression;
import interpreter.symbols.SymbolTable;
import interpreter.symbols.Exceptions.SymbolException;
/**
 * Sleep Command lets you delay the code from being for a period of desired time.<br>
 * The command receives the value in Milliseconds. 
 * @param Long parameter (Milliseconds). 
 * @author Arik Regev
 * @author Amit Koren
 */
public class SleepCommand implements Command {
	private MathExpression parameter;
	
	public SleepCommand(MathExpression parameter) {
		this.parameter = parameter;
	}

	@Override
	public boolean execute(SymbolTable symTable) throws ExecutionException {
		try {
			TimeUnit.MILLISECONDS.sleep((long) this.parameter.calculateNumber(symTable));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return true;
	}
	public static class Factory extends CommandFactory{
		@Override
		public Command create(List<String> tokens) throws ParseException {
			MathExpression exp = new ExpressionBuilder().createMathExpression(tokens);
			if (!tokens.isEmpty())
				throw new ParseException("Invalid expression at: " + tokens.get(0));
			return new SleepCommand(exp);
		}
		
	}

}
