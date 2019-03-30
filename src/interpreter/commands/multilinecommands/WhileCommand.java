package interpreter.commands.multilinecommands;

import interpreter.commands.Command;
import interpreter.expressionSolver.logic.BooleanExpression;
import interpreter.symboles.SymbolTable;
import interpreter.symboles.SymbolTable.SymbolException;

/**
 * Defining how our LOOP Command will behave 
 * 
 * @author Arik
 * @author Amit Koren
 */
public class WhileCommand extends MultiLineCommand {
	private BooleanExpression exp;
	
	public WhileCommand(BooleanExpression exp) {
		this.exp = exp;
	}
	@Override
	public void doCommand(SymbolTable symTable) throws SymbolException {
		while(exp.calculateLogic()) {
			for(Command c : this.subCommands) 
				c.doCommand(symTable);
		}
	}

}
