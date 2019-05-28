package interpreter.symbols;

import interpreter.symbols.Exceptions.BindPathNotExistException;

/**
 * Bind Symbol variables represents an active link to the Flight gear Simulator.<br>
 * With those links we can get information like altitude, speed and more parameters of the plane<br>
 * and instruct and send commands and basically control the entire AirCraft. 
 * @param String Path Name
 * @param SymbolTable symTable
 * @author Arik Regev
 * @author Amit Koren
 */
public class BindSymbol implements Symbol {
	@SuppressWarnings("unused")
	private String name;
	private String path;
	private SymbolTable symTable;
	
	private BindSymbol(String name, String path, SymbolTable symTable) {
		this.name = name;
		this.path = path;
		this.symTable = symTable;
	}
	
	public static BindSymbol createInstance(String name, String path, SymbolTable symTable) throws BindPathNotExistException{
		if (!symTable.simCom.containsVal(path))
			throw new BindPathNotExistException(name, path);
		return new BindSymbol(name, path, symTable);
	}
	@Override
	public void setValue(double val) {
		this.symTable.simCom.setVal(this.path, val);

	}

	@Override
	public double getValue() throws Exceptions.SymbolUnInitializedException{
		//Testing 
		return this.symTable.simCom.getVal(path);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof BindSymbol))
			return false;
		BindSymbol other = (BindSymbol) obj;
		return path.equals(other.path);
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