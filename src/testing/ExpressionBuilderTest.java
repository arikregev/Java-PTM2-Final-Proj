package testing;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import interpreter.Interpreter.ParseException;
import interpreter.expression.NumberExpression;
import interpreter.expression.SymbolExpression;
import interpreter.expression.builders.ExpressionBuilder;
import interpreter.expression.logic.BooleanExpression;
import interpreter.expression.logic.comparisonExpressions.BiggerThenExpression;
import interpreter.expression.math.AssignmentExpression;
import interpreter.expression.math.DivideExpression;
import interpreter.expression.math.MathExpression;
import interpreter.expression.math.MinusExpression;
import interpreter.expression.math.MultiplyExpression;
import interpreter.expression.math.PlusExpression;
import interpreter.expression.math.UnaryMinusExpression;
import interpreter.symboles.RegularSymbol;
import interpreter.symboles.SymbolTable;
import interpreter.symboles.SymbolTable.SymbolException;

class ExpressionBuilderTest {

	@Test
	void emptyExpressionThrowsTest() {
		ExpressionBuilder builder = new ExpressionBuilder(null);
		
		ParseException e = assertThrows(ParseException.class, () -> builder.createMathExpression(new ArrayList<String>()));
		assertEquals("Expression must not be empty", e.getMessage());
	}
	
	@Test
	void simpleBinaryExpressionTest() throws ParseException, SymbolException {
		ArrayList<String> tokensPlus = new ArrayList<String>();
		tokensPlus.add("5");
		tokensPlus.add("+");
		tokensPlus.add("4");
		ArrayList<String> tokensMinus = new ArrayList<String>();
		tokensMinus.add("5");
		tokensMinus.add("-");
		tokensMinus.add("4");
		ArrayList<String> tokensMul = new ArrayList<String>();
		tokensMul.add("5");
		tokensMul.add("*");
		tokensMul.add("4");
		ArrayList<String> tokensDiv = new ArrayList<String>();
		tokensDiv.add("5");
		tokensDiv.add("/");
		tokensDiv.add("4");
		
		PlusExpression expectedPlus = new PlusExpression(new NumberExpression(5), new NumberExpression(4));
		MinusExpression expectedMinus = new MinusExpression(new NumberExpression(5), new NumberExpression(4));
		MultiplyExpression expectedMul = new MultiplyExpression(new NumberExpression(5), new NumberExpression(4));
		DivideExpression expectedDiv = new DivideExpression(new NumberExpression(5), new NumberExpression(4));
		
		
		ExpressionBuilder builder = new ExpressionBuilder(null);
		
		MathExpression outputPlus = builder.createMathExpression(tokensPlus);
		MathExpression outputMinus = builder.createMathExpression(tokensMinus);
		MathExpression outputMul = builder.createMathExpression(tokensMul);
		MathExpression outputDiv = builder.createMathExpression(tokensDiv);
		
		assertEquals(expectedPlus, outputPlus);
		assertEquals(expectedMinus, outputMinus);
		assertEquals(expectedMul, outputMul);
		assertEquals(expectedDiv, outputDiv);
		
	}
	
	@Test
	void tooManyOpenParensTest() throws ParseException, SymbolException {
		ArrayList<String> tokensPlus = new ArrayList<String>();
		tokensPlus.add("(");
		tokensPlus.add("5");
		
		ExpressionBuilder builder = new ExpressionBuilder(null);
		
		ParseException e = assertThrows(ParseException.class, () -> builder.createMathExpression(tokensPlus));
		assertEquals("Too many open Parentheses", e.getMessage());
	}
	
	@Test
	void tooManyClosingParensTest() throws ParseException, SymbolException {
		ArrayList<String> tokensPlus = new ArrayList<String>();
		tokensPlus.add("(");
		tokensPlus.add("5");
		tokensPlus.add(")");
		tokensPlus.add(")");
		
		ExpressionBuilder builder = new ExpressionBuilder(null);
		
		ParseException e = assertThrows(ParseException.class, () -> builder.createMathExpression(tokensPlus));
		assertEquals("Too many closing Parentheses", e.getMessage());
	}
	
	@Test
	void plusplusExpressionTest() throws ParseException, SymbolException {
		ArrayList<String> tokensPlus = new ArrayList<String>();
		tokensPlus.add("5");
		tokensPlus.add("+");
		tokensPlus.add("4");
		tokensPlus.add("+");
		tokensPlus.add("3");
		
		PlusExpression expectedPlus = 
				new PlusExpression(
						new PlusExpression(
								new NumberExpression(5), 
								new NumberExpression(4)),
						new NumberExpression(3));
		
		
		ExpressionBuilder builder = new ExpressionBuilder(null);
		
		MathExpression outputPlus = builder.createMathExpression(tokensPlus);
		
		assertEquals(expectedPlus, outputPlus);
		
	}
	
	@Test
	void mulmulExpressionTest() throws ParseException, SymbolException {
		ArrayList<String> tokens = new ArrayList<String>();
		tokens.add("5");
		tokens.add("*");
		tokens.add("4");
		tokens.add("*");
		tokens.add("3");
		
		MultiplyExpression expectedPlus = 
				new MultiplyExpression(
						new MultiplyExpression(
								new NumberExpression(5), 
								new NumberExpression(4)),
						new NumberExpression(3));
		
		
		ExpressionBuilder builder = new ExpressionBuilder(null);
		
		MathExpression outputPlus = builder.createMathExpression(tokens);
		
		assertEquals(expectedPlus, outputPlus);
		
	}
	
	@Test
	void plusmulExpressionTest() throws ParseException, SymbolException {
		ArrayList<String> tokens = new ArrayList<String>();
		tokens.add("5");
		tokens.add("+");
		tokens.add("4");
		tokens.add("*");
		tokens.add("3");
		
		PlusExpression expectedPlus = 
				new PlusExpression(
						new NumberExpression(5),
						new MultiplyExpression(
								new NumberExpression(4), 
								new NumberExpression(3)));
		
		
		ExpressionBuilder builder = new ExpressionBuilder(null);
		
		MathExpression outputPlus = builder.createMathExpression(tokens);
		
		assertEquals(expectedPlus, outputPlus);
		
	}
	
	@Test
	void mulplusExpressionTest() throws ParseException, SymbolException {
		ArrayList<String> tokens = new ArrayList<String>();
		tokens.add("5");
		tokens.add("*");
		tokens.add("4");
		tokens.add("+");
		tokens.add("3");
		
		PlusExpression expectedPlus = 
				new PlusExpression(
						new MultiplyExpression(
								new NumberExpression(5), 
								new NumberExpression(4)),
						new NumberExpression(3));
		
		
		ExpressionBuilder builder = new ExpressionBuilder(null);
		
		MathExpression outputPlus = builder.createMathExpression(tokens);
		
		assertEquals(expectedPlus, outputPlus);
		
	}
	
	@Test
	void mulplusparensExpressionTest() throws ParseException, SymbolException {
		ArrayList<String> tokens = new ArrayList<String>();
		tokens.add("5");
		tokens.add("*");
		tokens.add("(");
		tokens.add("4");
		tokens.add("+");
		tokens.add("3");
		tokens.add(")");
		
		MultiplyExpression expectedPlus = 
				new MultiplyExpression(
						new NumberExpression(5), 
						new PlusExpression(
							new NumberExpression(4),
							new NumberExpression(3)));
		
		
		ExpressionBuilder builder = new ExpressionBuilder(null);
		
		MathExpression outputPlus = builder.createMathExpression(tokens);
		
		assertEquals(expectedPlus, outputPlus);
		
	}
	@Test
	void unaryminusExpressionTest() throws ParseException, SymbolException {
		ArrayList<String> tokens = new ArrayList<String>();
		tokens.add("-");
		tokens.add("5");
		
		UnaryMinusExpression expectedPlus = 
				new UnaryMinusExpression(new NumberExpression(5));
		
		ExpressionBuilder builder = new ExpressionBuilder(null);
		
		MathExpression outputPlus = builder.createMathExpression(tokens);
		
		assertEquals(expectedPlus, outputPlus);
		
	}
	
	@Test
	void unaryminusplusExpressionTest() throws ParseException, SymbolException {
		ArrayList<String> tokens = new ArrayList<String>();
		tokens.add("-");
		tokens.add("5");
		tokens.add("+");
		tokens.add("4");
		
		PlusExpression expectedPlus = 
				new PlusExpression(
						new UnaryMinusExpression(new NumberExpression(5)),
						new NumberExpression(4));
		
		ExpressionBuilder builder = new ExpressionBuilder(null);
		
		MathExpression outputPlus = builder.createMathExpression(tokens);
		
		assertEquals(expectedPlus, outputPlus);
		
	}
	
	@Test
	void plusunaryminusExpressionTest() throws ParseException, SymbolException {
		ArrayList<String> tokens = new ArrayList<String>();
		tokens.add("5");
		tokens.add("+");
		tokens.add("-");
		tokens.add("4");
		
		PlusExpression expectedPlus = 
				new PlusExpression(
						new NumberExpression(5),
						new UnaryMinusExpression(new NumberExpression(4)));
		
		ExpressionBuilder builder = new ExpressionBuilder(null);
		
		MathExpression outputPlus = builder.createMathExpression(tokens);
		
		assertEquals(expectedPlus, outputPlus);
		
	}
	@Test
	void plusunaryminusparensExpressionTest() throws ParseException, SymbolException {
		ArrayList<String> tokens = new ArrayList<String>();
		tokens.add("-");
		tokens.add("(");
		tokens.add("4");
		tokens.add("-");
		tokens.add("2");
		tokens.add(")");
		tokens.add("*");
		tokens.add("7");
		
		MultiplyExpression expectedPlus =
				new MultiplyExpression(
					new UnaryMinusExpression(
						new MinusExpression(
							new NumberExpression(4),
							new NumberExpression(2))),
					new NumberExpression(7));
		
		ExpressionBuilder builder = new ExpressionBuilder(null);
		
		MathExpression outputPlus = builder.createMathExpression(tokens);
		
		assertEquals(expectedPlus, outputPlus);
		
		// for the sports
		assertEquals(-14, outputPlus.calculateNumber(), 0.001);
		
	}
	
	@Test
	void assignExpressionTest() throws ParseException, SymbolException {
		ArrayList<String> tokens = new ArrayList<String>();
		tokens.add("a");
		tokens.add("=");
		tokens.add("4");
		
		SymbolTable table = new SymbolTable();
		table.addSymbol("a", new RegularSymbol(0));
		
		AssignmentExpression expectedPlus = 
				new AssignmentExpression(
						new SymbolExpression(new RegularSymbol(0)),
						new NumberExpression(4));
		
		ExpressionBuilder builder = new ExpressionBuilder(table);
		
		MathExpression outputPlus = builder.createMathExpression(tokens);
		
		assertEquals(expectedPlus, outputPlus);
		
		// check assignment
		assertEquals(4, outputPlus.calculateNumber(), 0.001);
		assertEquals(4, table.getSymbol("a").getValue(), 0.001);
		
	}
	
	@Test
	void assignplusExpressionTest() throws ParseException, SymbolException {
		ArrayList<String> tokens = new ArrayList<String>();
		tokens.add("a");
		tokens.add("=");
		tokens.add("4");
		tokens.add("+");
		tokens.add("3");
		
		SymbolTable table = new SymbolTable();
		table.addSymbol("a", new RegularSymbol(0));
		
		AssignmentExpression expectedPlus = 
				new AssignmentExpression(
						new SymbolExpression(new RegularSymbol(0)),
						new PlusExpression(
								new NumberExpression(4), 
								new NumberExpression(3)));
		
		ExpressionBuilder builder = new ExpressionBuilder(table);
		
		MathExpression outputPlus = builder.createMathExpression(tokens);
		
		assertEquals(expectedPlus, outputPlus);

		// check assignment
		assertEquals(7, outputPlus.calculateNumber(), 0.001);
		assertEquals(7, table.getSymbol("a").getValue(), 0.001);
	}
	
	@Test
	void assignassignExpressionTest() throws ParseException, SymbolException {
		ArrayList<String> tokens = new ArrayList<String>();
		tokens.add("a");
		tokens.add("=");
		tokens.add("b");
		tokens.add("=");
		tokens.add("4");
		
		SymbolTable table = new SymbolTable();
		table.addSymbol("a", new RegularSymbol(0));
		table.addSymbol("b", new RegularSymbol(0));
		
		AssignmentExpression expectedPlus = 
				new AssignmentExpression(
						new SymbolExpression(new RegularSymbol(0)),
						new AssignmentExpression(
								new SymbolExpression(new RegularSymbol(0)),
								new NumberExpression(4)));
		
		ExpressionBuilder builder = new ExpressionBuilder(table);
		
		MathExpression outputPlus = builder.createMathExpression(tokens);
		
		assertEquals(expectedPlus, outputPlus);
		
		// check assignment
		assertEquals(4, outputPlus.calculateNumber(), 0.001);
		assertEquals(4, table.getSymbol("a").getValue(), 0.001);
		assertEquals(4, table.getSymbol("b").getValue(), 0.001);
		
	}
	
	@Test
	void SimpleComparisonTest() throws ParseException, SymbolException {
		ArrayList<String> tokens = new ArrayList<String>();
		tokens.add("6");
		tokens.add(">");
		tokens.add("8");
		
		BiggerThenExpression expected = 
				new BiggerThenExpression(
						new NumberExpression(6),
						new NumberExpression(8));
		
		ExpressionBuilder builder = new ExpressionBuilder(null);
		
		BooleanExpression output = builder.createBooleanExpression(tokens);
		
		assertEquals(expected, output);
	}

}
