package interpreter.commands.factory;

import java.util.List;

import interpreter.Interpreter.ParseException;
import interpreter.commands.Command;
import interpreter.symboles.SymbolTable;
import interpreter.symboles.SymbolTable.SymbolException;

public abstract class CommandFactory {
	protected SymbolTable symTable;
	
	public CommandFactory(SymbolTable symTable) {
		this.symTable = symTable;
	}


	public abstract Command create(List<String> tokens) throws ParseException, SymbolException;
}
