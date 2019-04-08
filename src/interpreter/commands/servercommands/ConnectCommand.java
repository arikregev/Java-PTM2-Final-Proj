package interpreter.commands.servercommands;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
	private double port;
	private Socket conSock;
	private PrintWriter out;
	private BufferedReader in;

	public ConnectCommand(String ipAddr, MathExpression port) {
		this.ipAddr = ipAddr;
		this.port = port.calculateNumber();
		this.conSock = null;
		this.out = null;
		this.in = null;
	}

	@Override
	public void doCommand(SymbolTable symTable) throws SymbolException {
		try {
			this.conSock = new Socket(ipAddr, (int) port);
			this.out = new PrintWriter(conSock.getOutputStream(), true);
			this.in = new BufferedReader(new InputStreamReader(conSock.getInputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void closeConnectoin() throws IOException /* Check */ {
		if (this.in != null)
			this.in.close();
		if (this.out != null)
			this.out.close();
		if (this.conSock != null)
			this.conSock.close();
		System.out.println("Connection Closed!");

	}

	public static class Factory extends CommandFactory {
		public Factory(SymbolTable symTable) {
			super(symTable);
		}

		@Override
		public Command create(List<String> tokens) throws ParseException, SymbolException {
			if (!tokens.get(0).equals("connect"))
				throw new ParseException("Parse Error: " + tokens.get(0) + "is not a connect command!");
			tokens.remove(0);
			return new ConnectCommand(tokens.get(0), new ExpressionBuilder(symTable).createMathExpression(tokens));
		}

	}

}
