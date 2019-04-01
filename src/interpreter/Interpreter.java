package interpreter;

import java.io.EOFException;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import interpreter.commands.*;
import interpreter.commands.multilinecommands.MultiLineCommand;
import interpreter.symboles.SymbolTable;
import interpreter.symboles.SymbolTable.SymbolException;

public abstract class Interpreter {
	private HashMap<String, Command> commandMap;
	private SymbolTable symTable;
	MultiLineCommand mlc;
	
	private final String lexerRegex = "(\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}|-?\\d+\\.?\\d*|-?\\d*\\.?\\d+|\\\".*\\\"|==|!=|<|>|<=|>=|\\+|-|\\*|\\/|&&|\\|\\||!|=|\\(|\\)|\\{|\\}|\\w+)";

	public Interpreter() {
		commandMap = new HashMap<>();
		mlc = null;
		symTable = new SymbolTable();
		//**************************
		
		
	}
	
	public abstract String getLine() throws EOFException;
	
	public void run(){
		List<String> tokenStream = new LinkedList<String>();
		try {
			while(true) {
				// lexical analysis
				tokenStream.addAll(lexer(getLine()));
				
				// syntax + semantic analysis
				for (Command c: parseTokens(tokenStream)) {
					c.doCommand(symTable);
				}
				
			}
		}
		catch(EOFException e) {} 
		catch (SymbolException e) {
			// TODO print error message to user
		} 
	}
	
	// breaks a line into separate tokens
	public List<String> lexer(String line){
		Pattern r = Pattern.compile(lexerRegex);
		List<String> tokens = new LinkedList<String>();
		Matcher m = r.matcher(line);
		while (m.find()) {
			tokens.add(line.substring(m.start(), m.end()));
		}
		return tokens;
	}
	
	// parses as many commands as possible from tokenStream
	private List<Command> parseTokens(List<String> tokenStream) {
		List<Command> commands = new LinkedList<Command>();
		//TODO: implement
		return commands;
	}
	
	
}
