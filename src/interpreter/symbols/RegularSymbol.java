package interpreter.symbols;


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
	/**
	 * The method gives the ability in runtime to know if the variable is initialized or not.
	 * <br>Inside this class the method will always return true.
	 * <br>Example for an uninitialized variable:
	 * <pre>var x</pre>
	 * <br>Example for an initialized variable:
	 * <pre>var x = 5</pre>
	 * <pre>var y = bind /path</pre>
	 */
	@Override
	public boolean isInitialized() {
		return true;
	}

}
