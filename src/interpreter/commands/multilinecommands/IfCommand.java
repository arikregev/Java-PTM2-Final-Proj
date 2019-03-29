package interpreter.commands.multilinecommands;

import interpreter.commands.Command;
import interpreter.expressionSolver.logic.BooleanExpression;
import interpreter.symboles.SymbolTable;
import interpreter.symboles.SymbolTable.SymbolException;
/**
 * Defining how our IF Command will behave 
 * 
 * @author Arik Regev
 *
 */
public class IfCommand extends MultiLineCommand {
private BooleanExpression exp;
	
	public IfCommand(BooleanExpression exp) {
		this.exp = exp;
	}
	@Override
	public void doCommand(SymbolTable symTable) throws SymbolException {
		if(exp.calculateLogic()) {
			for(Command c : this.subCommands) 
				c.doCommand(symTable);
		}
	}

}
