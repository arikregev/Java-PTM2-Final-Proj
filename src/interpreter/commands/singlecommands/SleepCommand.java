package interpreter.commands.singlecommands;

import java.util.concurrent.TimeUnit;

import interpreter.commands.Command;
import interpreter.symboles.Symbol;
import interpreter.symboles.SymbolTable;
import interpreter.symboles.SymbolTable.SymbolException;

public class SleepCommand implements Command {
	private String varName;
	private String[] parameters;
	
	public SleepCommand(String varName, String[] parameters) {
		this.varName = varName;
		this.parameters = parameters;
	}

	@Override
	public void doCommand(SymbolTable symTable) throws SymbolException {
		Symbol sym = symTable.getSymbol(this.varName);
		sym.setValue(Double.parseDouble(parameters[0]));
		try {
			TimeUnit.MILLISECONDS.sleep((long) sym.getValue());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
