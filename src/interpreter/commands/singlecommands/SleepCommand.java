package interpreter.commands.singlecommands;

import java.util.concurrent.TimeUnit;

import interpreter.commands.Command;
import interpreter.symboles.SymbolTable;
import interpreter.symboles.SymbolTable.SymbolException;
/**
 * Sleep Command lets you delay the code from being for a period of desired time.<br>
 * The command receives the value in Milliseconds. 
 * @param Long parameter (Milliseconds). 
 * @author Arik Regev
 *
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

}
