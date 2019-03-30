package interpreter.commands.singlecommands;

import interpreter.commands.Command;
import interpreter.expressionSolver.math.MathExpression;
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

}
