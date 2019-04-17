package interpreter.commands.singlecommands;

import java.util.List;

import interpreter.Interpreter.ParseException;
import interpreter.commands.Command;
import interpreter.commands.factory.CommandFactory;
import interpreter.expression.builders.ExpressionBuilder;
import interpreter.expression.math.MathExpression;
import interpreter.symboles.Symbol;
import interpreter.symboles.SymbolTable;
import interpreter.symboles.SymbolTable.SymbolException;
/**
 * The Command was created in the purpose of 
 * having the ability to assign values to our variables in
 * our newly invented Language.
 * @param String varName.
 * @param MathExpression varValue.
 * @author Arik Regev
 * @author Amit Koren
 */
public class ExpressionCommand implements Command {

	private MathExpression varValue; //Can be an Expression or just a Number;
	
	public ExpressionCommand(MathExpression varValue) {
		this.varValue = varValue;
	}
	
	@Override
	public boolean execute(SymbolTable symTable) throws SymbolException {
		varValue.calculateNumber(symTable);
		return true;
	}
	public static class Factory extends CommandFactory{
		@Override
		public Command create(List<String> tokens) throws ParseException, SymbolException {
			MathExpression exp = new ExpressionBuilder().createMathExpression(tokens);
			if (!tokens.isEmpty())
				throw new ParseException("Invalid expression at: " + tokens.get(0));
			return new ExpressionCommand(exp);
		}
		
	}

}
