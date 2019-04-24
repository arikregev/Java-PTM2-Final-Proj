package interpreter.commands.servercommands;

import java.util.List;

import interpreter.Interpreter.ParseException;
import interpreter.commands.Command;
import interpreter.commands.ExecutionException;
import interpreter.commands.factory.CommandFactory;
import interpreter.symbols.SymbolTable;

/**
 * The Command was created in the purpose of having the ability to Disconnect 
 * the Telnet connection to the FlightGear Server and terminate our OpenDataServer
 * Using SimCom.
 * 
 * @author Arik Regev
 * @author Amit Koren
 */
public class DisconnectCommand implements Command {

	@Override
	public boolean execute(SymbolTable symTable) throws ExecutionException {
		symTable.simCom.disconnect();
		return true;
	}

	public static class Factory extends CommandFactory {
		@Override
		public Command create(List<String> tokens) throws ParseException {
			if (!tokens.isEmpty())
				throw new ParseException("Expression must be empty");
			return new DisconnectCommand();
		}
	}
}
