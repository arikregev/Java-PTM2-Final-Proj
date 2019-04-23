package interpreter.expression.builders;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import interpreter.Interpreter.ParseException;
import interpreter.expression.*;
import interpreter.expression.logic.*;
import interpreter.expression.logic.comparisonExpressions.*;
import interpreter.expression.math.*;
import interpreter.symbols.SymbolTable;
import interpreter.symbols.Exceptions.SymbolException;

public class ExpressionBuilder {
	
	public MathExpression createMathExpression(List<String> tokens) throws ParseException{
		return makeMathExpressionFromQueue(makeTokenQueue(tokens));		
	}
	
	public BooleanExpression createBooleanExpression(List<String> tokens) throws ParseException{
		return makeBooleanExpressionFromQueue(makeTokenQueue(tokens));		
	}
	

	private LinkedList<String> makeTokenQueue(List<String> tokens) throws ParseException{
		if (tokens.isEmpty())
			throw new ParseException("Expression must not be empty");
		LinkedList<String> queue = new LinkedList<String>();
		Stack<String> stack = new Stack<>();
		String prevToken = null;
		boolean stopDigest = false;
		while (!tokens.isEmpty()) {
			String token = tokens.get(0);
			switch(token) {
			
			case "&&":
			case "||":
				while (!stack.isEmpty() && stack.peek().matches("(\\-|\\+|\\*|\\/|~|=|<|>|<=|>=|==|!=|&&|\\|\\||!)")) {
					queue.addFirst(stack.pop());
				}
				stack.push(token);
				break;
				
			case "==":
			case "!=":
			case "<":
			case ">":
			case "<=":
			case ">=":
				while (!stack.isEmpty() && stack.peek().matches("(\\-|\\+|\\*|\\/|~|=|<|>|<=|>=|==|!=)")) {
					queue.addFirst(stack.pop());
				}
				stack.push(token);
				break;
				
			case "!":
				while (!stack.isEmpty() && stack.peek().matches("(\\-|\\+|\\*|\\/|~|=|<|>|<=|>=|==|!=|!)")) {
					queue.addFirst(stack.pop());
				}
				stack.push(token);
				break;
			
			
			case "=":
				while (!stack.isEmpty() && stack.peek().matches("(\\-|\\+|\\*|\\/|~)")) {
					queue.addFirst(stack.pop());
				}
				stack.push(token);
				break;
				
			case "-":
				if(prevToken == null || prevToken.matches("(\\-|\\+|\\*|\\/|=\\()")) {
					stack.push("~");  
					break;
				}
			case "+":
				while (!stack.isEmpty() && stack.peek().matches("(\\-|\\+|\\*|\\/|~)")) { //equals("/") || stack.peek().equals("*") 
						//|| stack.peek().equals("~"))) 
					queue.addFirst(stack.pop());
				}
				stack.push(token);
				break;
				
			case "*":
			case "/":
				while (!stack.isEmpty() && stack.peek().matches("(\\*|\\/|~)")) {
					queue.addFirst(stack.pop());
				}
				stack.push(token);
				break;
				
			case "(":
				if (isStartOfNewExpression(token, prevToken, stack.contains("("))) {
					stopDigest = true;
					break;
				}
				stack.push(token);
				break;
				
			case ")":
				while (!stack.isEmpty() && !(stack.peek().equals("(")))
					queue.addFirst(stack.pop());
				if (stack.isEmpty())
					throw new ParseException("Too many closing Parentheses");
				stack.pop();
				break;
				
			default:
				if (isStartOfNewExpression(token, prevToken, stack.contains("("))) {
					stopDigest = true;
					break;
				}
				queue.addFirst(token);
				break;
			}
			prevToken = token;
			if (stopDigest)
				break;
			tokens.remove(0);
		}
		while (!stack.isEmpty()) {
			String token = stack.pop();
			if (token.equals("("))
				throw new ParseException("Too many open Parentheses");
			queue.addFirst(token);
		}
		return queue;
	}
	
	private boolean isStartOfNewExpression(String token, String prevToken, boolean isInsideParens) throws ParseException{
		if (prevToken == null)
			return false;
		if (!prevToken.matches("(\\-|\\+|\\*|\\/|~|=|<|>|<=|>=|==|!=|&&|\\|\\||!|\\()"))
			if (isInsideParens)
				throw new ParseException("Illegal expressions at token: " + token);
			else
				return true;
		return false;
	}
	
	
	private MathExpression makeMathExpressionFromQueue(LinkedList<String> queue) throws ParseException {
		MathExpression finalResult = null;
		MathExpression left = null,right = null; // the sub expressions
		if (queue.isEmpty())
			throw new ParseException("Expression must not be empty");
		String token = queue.removeFirst();
		if(isBinaryOperator(token)) {
			right = makeMathExpressionFromQueue(queue);
			left = makeMathExpressionFromQueue(queue);
		}
		else if (token.equals("=")) {
			right = makeMathExpressionFromQueue(queue);
			SymbolExpression varName = new SymbolExpression(queue.removeFirst());
			return new AssignmentExpression(varName, right);
		}
		else if (token.equals("~")) {
			right = makeMathExpressionFromQueue(queue);
			return new UnaryMinusExpression(right);
		}
		switch(token) {
		case "+":
			finalResult = new PlusExpression(left, right);
			break;
		case "-":
			finalResult = new MinusExpression(left,right);
			break;
		case "*":
			finalResult = new MultiplyExpression(left,right);
			break;
		case "/":
			finalResult = new DivideExpression(left,right);
			break;
		default:
			try{ // check number
				return new NumberExpression(Double.parseDouble(token));
			} catch(NumberFormatException e) {}
			// check variable name
			if (token.matches("\\w+")) {
				return new SymbolExpression(token);
			}
			throw new ParseException("Illegal token: " + token);
		}
		
		return finalResult;
	}
	private BooleanExpression makeBooleanExpressionFromQueue(LinkedList<String> queue) throws ParseException{
		if (queue.isEmpty())
			throw new ParseException("Expression must not be empty");
		String token = queue.removeFirst();
		if(token.matches("(\\-|\\+|\\*|\\/|~|=)")) {
			throw new ParseException("Illegal boolean expression");
		}
		if(token.matches("(==|!=|<=|>=|<|>)")) {
			return makeComparisonExpression(token, queue);
		} else if(token.matches("(&&|\\|\\|)")){
			return makeBinaryLogicExpression(token, queue);
		} else if(token.equals("!"))
			return new NotExpression(makeBooleanExpressionFromQueue(queue));
		throw new ParseException("Illegal token: " + token);
	}
	
	private BooleanExpression makeComparisonExpression(String token, LinkedList<String> queue) throws ParseException {
		MathExpression rightMath = makeMathExpressionFromQueue(queue);
		MathExpression leftMath = makeMathExpressionFromQueue(queue);
		switch(token) {
		case "==":
			return new EqualsExpression(leftMath, rightMath);
		case "!=":
			return new NotEqualsExpression(leftMath, rightMath);
		case "<=":
			return new LowerEqualsExpression(leftMath, rightMath);
		case ">=":
			return new BiggerEqualsExpression(leftMath, rightMath);
		case "<":
			return new LowerThenExpression(leftMath, rightMath);
		case ">":
			return new BiggerThenExpression(leftMath, rightMath);
		default:
			throw new ParseException("Illegal token: " + token);
		}
	}
	
	private BooleanExpression makeBinaryLogicExpression(String token, LinkedList<String> queue) throws ParseException {
		BooleanExpression rightBool = makeBooleanExpressionFromQueue(queue);
		BooleanExpression leftBool = makeBooleanExpressionFromQueue(queue);
		switch(token) {
		case "||":
			return new OrExpression(leftBool, rightBool);
		case "&&":
			return new AndExpression(leftBool, rightBool);
		default:
			throw new ParseException("Illegal token: " + token);
		}
	}
	
	private static boolean isBinaryOperator(String s) {
		return s.matches("(\\-|\\+|\\*|\\/)");
	}

}
