package interpreter;

import java.util.ArrayList;
import java.util.HashMap;

import interpreter.commands.*;
import interpreter.commands.multilinecommands.MultiLineCommand;
import interpreter.symboles.SymbolTable;

public class Interpreter {
	private HashMap<String, Command> map;
	private SymbolTable symTable;
	MultiLineCommand mlc;

	public Interpreter() {
		map = new HashMap<>();
		mlc = null;
		symTable = new SymbolTable();
	}

	public ArrayList<String[]> lexer(String s) {

		return null;
	}

	public void parser(String[] s) {

	}
}
