package interpreter.commands.singlecommands;

import java.util.List;

import interpreter.Interpreter.ParseException;
import interpreter.commands.Command;
import interpreter.commands.factory.CommandFactory;
import interpreter.expression.builders.MathExpressionBuilder;
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
		public void printMe();
	}
	private Printable value;
	
	public PrintCommand(String st) {
		this.value = ()->{System.out.println(st);}; //Inserting into value the printMe command with the settings of
	}//how to execute it
	public PrintCommand(MathExpression exp) {
		this.value = ()->{System.out.println(exp.calculateNumber());}; // same here
	}
	@Override
	public void doCommand(SymbolTable symTable) throws SymbolException {
		value.printMe();
	}
	public static class Factory implements CommandFactory{
		@Override
		public Command create(List<String> tokens) throws ParseException {
			if(tokens.isEmpty()) throw new ParseException();
			String s = tokens.get(0);
			if(tokens.size() == 1 && s.startsWith("\"") && s.endsWith("\""))
				return new PrintCommand(s.substring(1, s.length()-1));
			return new PrintCommand(new MathExpressionBuilder().create(tokens));
		}
		
	}

}
