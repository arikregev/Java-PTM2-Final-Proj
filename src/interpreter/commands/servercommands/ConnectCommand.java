package interpreter.commands.servercommands;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

import interpreter.Interpreter.ParseException;
import interpreter.commands.Command;
import interpreter.commands.factory.CommandFactory;
import interpreter.expression.builders.ExpressionBuilder;
import interpreter.expression.math.MathExpression;
import interpreter.symboles.SymbolTable;
import interpreter.symboles.SymbolTable.SymbolException;

/**
 * The Command was created in the purpose of having the ability to connect to
 * the FlightGear Server
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
	private Socket conSock;
	private PrintWriter out;

	public ConnectCommand(String ipAddr, MathExpression port) {
		this.ipAddr = ipAddr;
		this.port = port;
		this.conSock = null;
		this.out = null;
	}

	@Override
	public boolean execute(SymbolTable symTable) throws SymbolException {
		try {
			this.conSock = new Socket(ipAddr, (int) port.calculateNumber(symTable));
			this.out = new PrintWriter(conSock.getOutputStream(), true);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	public void closeConnectoin() throws IOException /* Check */ {
		if (this.out != null)
			this.out.close();
		if (this.conSock != null)
			this.conSock.close();
		System.out.println("Connection Closed!");

	}

	public static class Factory extends CommandFactory {
		@Override
		public Command create(List<String> tokens) throws ParseException, SymbolException {
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
