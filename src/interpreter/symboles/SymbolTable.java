package interpreter.symboles;

import java.util.HashMap;
/**
 * Symbol table store symbols declared by the instructions sent into the software.<br>
 * The symbol table is the "bridge" between the software and the flight simulator.<br>
 * All declared variables will create Symbols that will be stored inside the HashMap for easy and fast Access.  
 * @param HashMap <String,Symbol> symTable.
 * @author Arik Regev
 * @author Amit Koren
 */
public class SymbolTable {
	@SuppressWarnings("serial")
	public static class SymbolException extends Exception {
		public final String symbolName;

		public SymbolException(String symbolName) {
			super();
			this.symbolName = symbolName;
		}
	}
	@SuppressWarnings("serial")
	public static class SymbolNotExistException extends SymbolException {

		public SymbolNotExistException(String symbolName) {
			super(symbolName);
		}
	}
	@SuppressWarnings("serial")
	public static class SymbolAlreadyExistException extends SymbolException {

		public SymbolAlreadyExistException(String symbolName) {
			super(symbolName);
		}
	}
	@SuppressWarnings("serial")
	public static class SymbolUnInitializedException extends SymbolException {

		public SymbolUnInitializedException(String symbolName) {
			super(symbolName);
		}
	}
	
	private HashMap<String,Symbol> symTable;
	private double returnValue = 0;

	public SymbolTable() {
		symTable = new HashMap<String, Symbol>();
	}
	public Symbol getSymbol(String s) throws SymbolNotExistException {
		if(!symTable.containsKey(s)) throw new SymbolNotExistException(s);
		return symTable.get(s);
	}
	public void addSymbol(String s, Symbol sym) throws SymbolAlreadyExistException{
		if(symTable.containsKey(s)) throw new SymbolAlreadyExistException(s);
		symTable.put(s, sym);
	}
	public boolean hasSymbol(String s) {
		return symTable.containsKey(s);
	}
	
	/**
	 * @return the returnValue
	 */
	public double getReturnValue() {
		return returnValue;
	}
	/**
	 * @param returnValue the returnValue to set
	 */
	public void setReturnValue(double returnValue) {
		this.returnValue = returnValue;
	}
}
