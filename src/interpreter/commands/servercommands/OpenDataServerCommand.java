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

public class OpenDataServerCommand implements Command {
	private MathExpression port;
	private MathExpression rate;
//	private volatile boolean stop;
//	private ServerSocket recvSocket;
//	private Socket clientSocket;
//	private BufferedReader br;
//	private HashMap<String, Symbol> symPtr;

	public OpenDataServerCommand(MathExpression port, MathExpression rate) {
		this.port = port;
		this.rate = rate;
//		this.stop = false;
//		this.symPtr = new HashMap<>();
	}

	@Override
	public boolean execute(SymbolTable symTable) throws ExecutionException {
		try {
			symTable.simCom.openDataServer((int)port.calculateNumber(symTable),
					(int)rate.calculateNumber(symTable));
		} catch (IOException e) {
			throw new IOExceptionWrapper(e);
		}
		return true;
		
		/*int actualPort = (int)port.calculateNumber(symTable);
		Thread t1 = new Thread(() -> {
			try {
				this.recvSocket = new ServerSocket(actualPort);
				this.clientSocket = recvSocket.accept();
				this.br = new BufferedReader(new InputStreamReader(this.clientSocket.getInputStream()));
				String line = br.readLine();
				while(!stop) {
					if(line != null) {
						String[] parsed = line.split(",");
						
						
					}
					Thread.sleep(100);
				}
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
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
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
		t1.start();*/
	}


	
	public static class Factory extends CommandFactory {
		@Override
		public Command create(List<String> tokens) throws ParseException {
			MathExpression portExp = new ExpressionBuilder().createMathExpression(tokens);
			MathExpression rateExp = new ExpressionBuilder().createMathExpression(tokens);
			if (!tokens.isEmpty())
				throw new ParseException("Invalid expression at: " + tokens.get(0));
			return new OpenDataServerCommand(portExp, rateExp);
		}
	}
}
