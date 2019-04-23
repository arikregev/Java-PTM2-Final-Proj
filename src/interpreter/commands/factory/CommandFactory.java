package interpreter.commands.factory;

import java.util.List;

import interpreter.Interpreter.ParseException;
import interpreter.commands.Command;
import interpreter.symbols.SymbolTable;
import interpreter.symbols.Exceptions.SymbolException;

public abstract class CommandFactory {

	public abstract Command create(List<String> tokens) throws ParseException;
}
