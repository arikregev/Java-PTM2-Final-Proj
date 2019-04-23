package interpreter.symbols;

import interpreter.expression.NumberExpression;
import interpreter.symbols.Exceptions.SymbolUnInitializedException;

/**
 * Regular Symbol represents variables that are numeric based like int, double. 
 * <br>
 * @param Double
 * @author Arik Regev
 * @author Amit Koren
 */
public class RegularSymbol implements Symbol {

	private String symName;
	
	public RegularSymbol(String symName) {
		this.symName = symName;
	}

	private Double value = null;

	@Override
	public void setValue(double val) {
		this.value = val;
	}

	@Override
	public double getValue() throws Exceptions.SymbolUnInitializedException{
		if (this.value == null)
			throw new Exceptions.SymbolUnInitializedException(symName);
		return this.value;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof RegularSymbol))
			return false;
		RegularSymbol other = (RegularSymbol) obj;
		return Double.doubleToLongBits(value) == Double.doubleToLongBits(other.value);
	}
	@Override
	public boolean isInitialized() {
		return true;
	}

}
