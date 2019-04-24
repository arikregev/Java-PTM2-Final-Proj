package interpreter.commands.servercommands;

import java.io.IOException;
import java.util.List;

import interpreter.Interpreter.ParseException;
import interpreter.commands.Command;
import interpreter.commands.ExecutionException;
import interpreter.commands.factory.CommandFactory;
import interpreter.expression.builders.ExpressionBuilder;
import interpreter.expression.math.MathExpression;
import interpreter.symbols.SymbolTable;

/**
 * The Command was created in the purpose of having the ability to connect to
 * the FlightGear Server and send commands to it.
 * The command is initiating the Connect method on the SimCom Class.
 * 
 * @author Arik Regev
 * @author Amit Koren
 */
public class ConnectCommand implements Command {
	@SuppressWarnings("serial")
	public static class ConnectException extends Exception {
		public ConnectException(String s) {
			super(s);
		}
	}

	private String ipAddr;
	private MathExpression port;

	public ConnectCommand(String ipAddr, MathExpression port) {
		this.ipAddr = ipAddr;
		this.port = port;
	}

	@Override
	public boolean execute(SymbolTable symTable) throws ExecutionException {
		try {
			symTable.simCom.connect(this.ipAddr, (int)this.port.calculateNumber(symTable));
		} catch (IOException e) {
			throw new IOExceptionWrapper(e);
		}
		return true;
	}

	public static class Factory extends CommandFactory {
		@Override
		public Command create(List<String> tokens) throws ParseException {
			if (tokens.isEmpty())
				throw new ParseException("Expression must not be empty");
			String ipAddr = tokens.remove(0);
			MathExpression mathExp = new ExpressionBuilder().createMathExpression(tokens);
			if (!tokens.isEmpty())
				throw new ParseException("Invalid expression at: " + tokens.get(0));
			return new ConnectCommand(ipAddr, mathExp);
		}

	}

}
