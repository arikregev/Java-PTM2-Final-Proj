package interpreter.expression.builders;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import interpreter.Interpreter.ParseException;
import interpreter.expression.*;
import interpreter.expression.math.*;
import interpreter.symboles.SymbolTable;
import interpreter.symboles.SymbolTable.SymbolException;

public class MathExpressionBuilder {
	private SymbolTable symTable;
	public MathExpressionBuilder(SymbolTable symTable) {
		this.symTable = symTable;
	}
	public MathExpression create(List<String> tokens) throws ParseException, SymbolException{
		return makeExpressionFromQueue(makeTokenQueue(tokens));		
	}
	private LinkedList<String> makeTokenQueue(List<String>tokens) throws ParseException, SymbolException{
		LinkedList<String> queue = new LinkedList<String>();
		Stack<String> stack = new Stack<>();
		String prevToken = null;
		for(String token: tokens) {
			switch(token) {
			case "=":
				// TODO: not sure about this fucker
				while (!stack.isEmpty() && (stack.peek().equals("/") || stack.peek().equals("*") 
						|| stack.peek().equals("~") || stack.peek().equals("+") || stack.peek().equals("-"))) {
					queue.addFirst(stack.pop());
				}
				stack.push(token);
				break;
				
			case "-":
				if(prevToken == null || prevToken.matches("[\\\\-\\*\\/=]")) {
					stack.push("~");  
				}
			case "+":
				while (!stack.isEmpty() && (stack.peek().equals("/") || stack.peek().equals("*") 
						|| stack.peek().equals("~"))) {
					queue.addFirst(stack.pop());
				}
				stack.push(token);
				break;
				
			case "*":
			case "/":
				while (!stack.isEmpty() && stack.peek().equals("~")) {
					queue.addFirst(stack.pop());
				}
				stack.push(token);
				break;
				
			case "(":
				stack.push(token);
				break;
				
			case ")":
				while (!stack.isEmpty() && !(stack.peek().equals("(")))
					queue.addFirst(stack.pop());
				stack.pop();
				break;
				
			default:
				queue.addFirst(token);
				break;
				
				/*
				
				*/
			}
			prevToken = token;
		}
		return queue;
	}
	
	private MathExpression makeExpressionFromQueue(LinkedList<String> queue) throws ParseException, SymbolException {
		MathExpression finalResult = null;
		MathExpression left = null,right = null; // the sub expressions
		String token = queue.removeFirst();
		if(isBinaryOperator(token)) {
			right = makeExpressionFromQueue(queue);
			left = makeExpressionFromQueue(queue);
		}
		else if (token.equals("=")) {
			right = makeExpressionFromQueue(queue);
			SymbolExpression varName = new SymbolExpression(symTable.getSymbol(queue.removeFirst()));
			return new AssignmentExpression(varName, right);
		}
		else if (token.equals("~")) {
			right = makeExpressionFromQueue(queue);
			return new UnaryMinusExpression(right);
		}
		switch(token.charAt(0)) {
		case '+':
			finalResult = new PlusExpression(left, right);
			break;
		case '-':
			finalResult = new MinusExpression(left,right);
			break;
		case '*':
			finalResult = new MultiplyExpression(left,right);
			break;
		case '/':
			finalResult = new DivideExpression(left,right);
			break;
		default:
			try{ // check number
				return new NumberExpression(Double.parseDouble(token));
			} catch(NumberFormatException e) {}
			// check variable name
			if (token.matches("\\w+")) {
				return new SymbolExpression(symTable.getSymbol(token));
			}
			throw new ParseException("Illegal token: " + token);
		}
		
		return finalResult;
	}
	private static boolean isBinaryOperator(String s) {
		return s.matches("[\\\\-\\*\\/]");
	}

}
