package interpreter.symbols;

import interpreter.symbols.Exceptions.BindPathNotExistException;
import interpreter.symbols.Exceptions.SymbolUnInitializedException;

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
	@Override
	public boolean isInitialized() {
		return true;
	}

}