package interpreter.commands.multilinecommands;

import java.util.List;

import interpreter.Interpreter.ParseException;
import interpreter.commands.Command;
import interpreter.commands.factory.CommandFactory;
import interpreter.expression.logic.BooleanExpression;
import interpreter.symboles.SymbolTable;
import interpreter.symboles.SymbolTable.SymbolException;

/**
 * Defining how our LOOP Command will behave 
 * 
 * @author Arik Regev
 * 
 */
public class WhileCommand extends ControlCommand{
	
	private BooleanExpression exp;
	
	public WhileCommand(BooleanExpression exp) {
		this.exp = exp;
	}
	@Override
	public void execute(SymbolTable symTable) throws SymbolException {
		while(exp.calculateLogic()) {
			innerCommand.execute(symTable);
		}
	}
	public static class Factory extends CommandFactory{
		public Factory(SymbolTable symTable) {
			super(symTable);
		}
		@Override
		public Command create(List<String> tokens) throws ParseException, SymbolException {
			// TODO Auto-generated method stub
			return null;
		}
		
	}

}
