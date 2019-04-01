package interpreter;

import java.io.EOFException;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import interpreter.commands.*;
import interpreter.commands.factory.CommandFactory;
import interpreter.commands.multilinecommands.*;
import interpreter.commands.servercommands.*;
import interpreter.commands.singlecommands.*;
import interpreter.symboles.SymbolTable;
import interpreter.symboles.SymbolTable.SymbolException;

public abstract class Interpreter {
	@SuppressWarnings("serial")
	public static class ParseException extends Exception {}
	
	private HashMap<String, CommandFactory> commandMap;
	private SymbolTable symTable;
	private Stack<MultiLineCommand> blockStack;
	private ControlCommand ctrl;
	
	private final String lexerRegex = "(\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}|-?\\d+\\.?\\d*|-?\\d*\\.?\\d+|\\\".*\\\"|==|!=|<|>|<=|>=|\\+|-|\\*|\\/|&&|\\|\\||!|=|\\(|\\)|\\{|\\}|\\w+)";

	public Interpreter() {
		commandMap = new HashMap<>();
		blockStack = new Stack<>();
		symTable = new SymbolTable();
		
		commandMap.put("var", new VarCommand.Factory());
		commandMap.put("openDataServer", new OpenDataServerCommand.Factory());
		commandMap.put("connect", new ConnectCommand.Factory());
		commandMap.put("while", new WhileCommand.Factory());
		commandMap.put("if", new IfCommand.Factory());
		commandMap.put("print", new PrintCommand.Factory());
		commandMap.put("sleep", new SleepCommand.Factory());
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
		} catch (ParseException e) {
			// TODO print error message to user
		} 
	}
	
	// breaks a line into separate tokens
	private List<String> lexer(String line){
		Pattern r = Pattern.compile(lexerRegex);
		List<String> tokens = new LinkedList<String>();
		Matcher m = r.matcher(line);
		while (m.find()) {
			tokens.add(line.substring(m.start(), m.end()));
		}
		return tokens;
	}
	
	// parses as many commands as possible from tokenStream
	private List<Command> parseTokens(List<String> tokenStream) throws ParseException{
		List<Command> commands = new LinkedList<Command>();
		while (!tokenStream.isEmpty()) {
			String openingToken = tokenStream.get(0); 
			if (openingToken.equals("{")) {
				MultiLineCommand n = new MultiLineCommand();
				if(ctrl != null) {
					ctrl.addInner(n);
					n.setOwner(ctrl);
				}
				blockStack.push(n);
				
				tokenStream.remove(0);
				ctrl = null;
				
				continue;
			}
			if (openingToken.equals("}")) {
				if(blockStack.isEmpty()) throw new ParseException(); 
				MultiLineCommand block = blockStack.pop();
				if(blockStack.isEmpty())
					if(block.getOwner() == null)
						commands.add(block);
					else
						commands.add(block.getOwner());
				
				tokenStream.remove(0);
				ctrl = null;
				
				continue;
			}
			List<String> commandTokens = new LinkedList<>();
			while (!tokenStream.isEmpty() && !tokenStream.get(0).equals("}")
					&& !tokenStream.get(0).equals("{")) {
				commandTokens.add(tokenStream.remove(0));
			}
			Command cmd = parseCommand(commandTokens);
			if(!blockStack.isEmpty())
				blockStack.peek().addSubCommand(cmd);
			else
				commands.add(cmd);
		}
		return commands;
	}
	private Command parseCommand(List<String> tokens) throws ParseException{
		if(!commandMap.containsKey(tokens.get(0)))
			return new ExpressionCommand.Factory().create(tokens);
		return commandMap.get(tokens.remove(0)).create(tokens);
	}
	
}
