package interpreter.symbols;

import interpreter.symbols.Exceptions.SymbolUnInitializedException;

/**
 * Symbol Interface, will represent variables declared by the code received.<br>
 * Every Symbol created will be inserted into the Symbol table for control.
 * @author Arik Regev
 * @author Amit Koren
 */
public interface Symbol {
	public void setValue(double val);
	public double getValue() throws Exceptions.SymbolUnInitializedException;
	public boolean isInitialized();
}
