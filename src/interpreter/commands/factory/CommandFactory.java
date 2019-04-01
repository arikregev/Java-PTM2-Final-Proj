package interpreter.commands.factory;

import java.util.List;

import interpreter.Interpreter.ParseException;
import interpreter.commands.Command;

public interface CommandFactory {
	public Command create(List<String> tokens) throws ParseException;
}
