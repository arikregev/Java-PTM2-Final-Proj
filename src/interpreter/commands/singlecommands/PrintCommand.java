package interpreter.commands.singlecommands;

import java.util.List;

import interpreter.Interpreter.ParseException;
import interpreter.commands.Command;
import interpreter.commands.factory.CommandFactory;
import interpreter.expression.builders.ExpressionBuilder;
import interpreter.expression.math.MathExpression;
import interpreter.symboles.SymbolTable;
import interpreter.symboles.SymbolTable.SymbolException;
/**
 * The Print command allows you to print to the screen Strings and MathExpressions.
 * @author Arik Regev
 * @author Amit Koren
 */
public class PrintCommand implements Command {
	/**
	 * Private interface that when implemented can be used to print Strings and MathExpressions<br>
	 * Using Lambda Expression.
	 * @author Arik Regev
	 *
	 */
	private static interface Printable{
		public void printMe(SymbolTable symTable) throws SymbolException;
	}
	private Printable value;
	
	public PrintCommand(String st) {
		this.value = (SymbolTable symTable)->{System.out.println(st);}; //Inserting into value the printMe command with the settings of
	}//how to execute it
	public PrintCommand(MathExpression exp) {
		this.value = (SymbolTable symTable)->{System.out.println(exp.calculateNumber(symTable));}; // same here
	}
	@Override
	public boolean execute(SymbolTable symTable) throws SymbolException {
		value.printMe(symTable);
		return true;
	}
	public static class Factory extends CommandFactory{

		@Override
		public Command create(List<String> tokens) throws ParseException, SymbolException {
			if (tokens.isEmpty())
				throw new ParseException("Expression must not be empty");
			String s = tokens.get(0);
			if(tokens.size() == 1 && s.startsWith("\"") && s.endsWith("\""))
				return new PrintCommand(s.substring(1, s.length()-1));
			MathExpression mathExp = new ExpressionBuilder().createMathExpression(tokens);
			if (!tokens.isEmpty())
				throw new ParseException("Invalid expression at: " + tokens.get(0));
			return new PrintCommand(mathExp);
		}
		
	}

}
