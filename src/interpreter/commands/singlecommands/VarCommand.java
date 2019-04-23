package interpreter.commands.singlecommands;

import java.util.List;

import interpreter.Interpreter.ParseException;
import interpreter.commands.Command;
import interpreter.commands.ExecutionException;
import interpreter.commands.factory.CommandFactory;
import interpreter.expression.builders.ExpressionBuilder;
import interpreter.expression.math.MathExpression;
import interpreter.symbols.BindSymbol;
import interpreter.symbols.Exceptions;
import interpreter.symbols.RegularSymbol;
import interpreter.symbols.Symbol;
import interpreter.symbols.SymbolTable;
import interpreter.symbols.Exceptions.SymbolAlreadyExistException;
import interpreter.symbols.Exceptions.SymbolException;
/**
 * Every Var Command represents a link to a parameter in the aircraft.<br>
 * TODO ...
 * @param String SymbolName
 * @param SymbolFactory
 * @author Arik Regev
 * @author Amit Koren
 */
public class VarCommand implements Command {
	/**
	 * Symbol Factory is a static interface for the purpose of creating new symbols<br>
	 * using Lambda Expressions and inserting the newly created symbol into the Symbol Table.
	 * @author Arik Regev
	 */
	private static interface SymbolFactory{
		public void createSymbol(SymbolTable symTable) throws Exceptions.SymbolException;
	}
	private SymbolFactory s;
	
	public VarCommand(String symName) {
		this.s = (symTable)->{
			symTable.addSymbol(symName, null);
		};
	}
	
	public VarCommand(String symName, MathExpression exp) {
		this.s = (symTable)->{
			symTable.addSymbol(symName, new RegularSymbol(symName));
			exp.calculateNumber(symTable);
		};
	}
		
	public VarCommand(String symName, String path) {
		this.s = (symTable)->{
			symTable.addSymbol(symName, BindSymbol.createInstance(symName, path, symTable));
		};
		// a = bind hgjkgkjh
	}

	@Override
	public boolean execute(SymbolTable symTable) throws ExecutionException {
		s.createSymbol(symTable);
		return true;
	}
	public static class Factory extends CommandFactory{
		@Override
		public Command create(List<String> tokens) throws ParseException {
			if (tokens.isEmpty())
				throw new ParseException("Expression must not be empty");
			String symName = tokens.get(0);
			if (tokens.size() == 1)
				return new VarCommand(symName);
			if (tokens.get(2).equals("bind")) {
				if (tokens.size() > 4)
					throw new ParseException("Var (bind) expression too long");
				return new VarCommand(symName, tokens.get(3));
			}
			MathExpression exp = new ExpressionBuilder().createMathExpression(tokens);
			if (!tokens.isEmpty())
				throw new ParseException("Invalid expression at: " + tokens.get(0));
			return new VarCommand(symName, exp);
		}
		
	}

}
