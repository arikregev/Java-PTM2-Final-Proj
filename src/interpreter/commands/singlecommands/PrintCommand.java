package interpreter.commands.singlecommands;

import interpreter.commands.Command;
import interpreter.expressionSolver.math.MathExpression;
import interpreter.symboles.SymbolTable;
import interpreter.symboles.SymbolTable.SymbolException;

public class PrintCommand implements Command {
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
