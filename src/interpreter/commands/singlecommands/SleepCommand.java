package interpreter.commands.singlecommands;

import java.util.List;
import java.util.concurrent.TimeUnit;

import interpreter.Interpreter.ParseException;
import interpreter.commands.Command;
import interpreter.commands.factory.CommandFactory;
import interpreter.symboles.SymbolTable;
import interpreter.symboles.SymbolTable.SymbolException;
/**
 * Sleep Command lets you delay the code from being for a period of desired time.<br>
 * The command receives the value in Milliseconds. 
 * @param Long parameter (Milliseconds). 
 * @author Arik Regev
 * @author Amit Koren
 */
public class SleepCommand implements Command {
	private long parameter;
	
	public SleepCommand(long parameter) {
		this.parameter = parameter;
	}

	@Override
	public void doCommand(SymbolTable symTable) throws SymbolException {
		try {
			TimeUnit.MILLISECONDS.sleep(this.parameter);
		} catch (InterruptedException e) {
			e.printStackTrace();
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
