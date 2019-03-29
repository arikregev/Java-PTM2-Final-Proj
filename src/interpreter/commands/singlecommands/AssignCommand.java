package interpreter.commands.singlecommands;

import interpreter.commands.Command;
import interpreter.expressionSolver.math.MathExpression;
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
 */
public class AssignCommand implements Command {
	private String varName;
	private MathExpression varValue; //Can be an Expression or just a Number;
	
	public AssignCommand(String varName, MathExpression varValue) {
		this.varName = varName;
		this.varValue = varValue;
	}
	
	@Override
	public void doCommand(SymbolTable symTable) throws SymbolException {
		Symbol sym = symTable.getSymbol(this.varName);
		sym.setValue(varValue.calculateNumber());
	}

}
