package interpreter.commands.multilinecommands;


import java.util.List;

import interpreter.Interpreter.ParseException;
import interpreter.commands.Command;
import interpreter.commands.factory.CommandFactory;
import interpreter.expression.logic.BooleanExpression;
import interpreter.symboles.SymbolTable;
import interpreter.symboles.SymbolTable.SymbolException;
/**
 * Defining how our IF Command will behave 
 * 
 * @author Arik Regev
 *
 */
public class IfCommand extends ControlCommand{
	private BooleanExpression exp;
	
	public IfCommand(BooleanExpression exp) {
		this.exp = exp;
	}
	@Override
	public void doCommand(SymbolTable symTable) throws SymbolException {
		if(exp.calculateLogic()) { 
			innerCommand.doCommand(symTable);
		}
	}
	public static class Factory implements CommandFactory{
		@Override
		public Command create(List<String> tokens) throws ParseException {
			// TODO Auto-generated method stub
			return null;
		}
		
	}

}
