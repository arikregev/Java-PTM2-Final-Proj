package interpreter.commands.servercommands;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

import interpreter.Interpreter.ParseException;
import interpreter.commands.Command;
import interpreter.commands.factory.CommandFactory;
import interpreter.expression.builders.ExpressionBuilder;
import interpreter.expression.math.MathExpression;
import interpreter.symboles.SymbolTable;
import interpreter.symboles.SymbolTable.SymbolException;

public class OpenDataServerCommand implements Command {
	private int port;
	private double rate;
	private volatile boolean stop;
	private ServerSocket recvSocket;
	private Socket clientSocket;
	private BufferedReader br;

	public OpenDataServerCommand(MathExpression port, MathExpression rate) {
		this.port = (int) port.calculateNumber();
		this.rate = rate.calculateNumber();
		this.stop = false;
	}

	@Override
	public void execute(SymbolTable symTable) throws SymbolException {
		Thread t1 = new Thread(() -> {
			try {
				this.recvSocket = new ServerSocket(port);
				this.clientSocket = recvSocket.accept();
				this.br = new BufferedReader(new InputStreamReader(this.clientSocket.getInputStream()));
			} catch (IOException e) {
				try {
					if (this.br != null)
						this.br.close();
					if (this.clientSocket != null)
						this.clientSocket.close();
					if (this.recvSocket != null)
						this.recvSocket.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				e.printStackTrace();
			}
			try {
				String lineFromSim = br.readLine();
				while(lineFromSim != null) {
					
					lineFromSim = br.readLine();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		});
		t1.start();

	}

	public void stop() {
		this.stop = true;
	}

	public static class Factory extends CommandFactory {
		public Factory(SymbolTable symTable) {
			super(symTable);
		}

		@Override
		public Command create(List<String> tokens) throws ParseException, SymbolException {
			if (tokens.get(0).equals("openDataServer"))
				throw new ParseException("Parse Error:" + tokens.get(0) + "is not a openDataServer command!");
			tokens.remove(0);
			return new OpenDataServerCommand(new ExpressionBuilder(symTable).createMathExpression(tokens.subList(0, 0)),
					new ExpressionBuilder(symTable).createMathExpression(tokens.subList(1, 1)));
		}
	}
}
