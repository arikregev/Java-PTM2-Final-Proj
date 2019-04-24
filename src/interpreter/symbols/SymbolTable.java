package interpreter.symbols;

import java.util.HashMap;

import interpreter.symbols.Exceptions.SymbolAlreadyExistException;
import interpreter.symbols.Exceptions.SymbolUnInitializedException;
import simulator.SimulatorCom;
/**
 * Symbol table store symbols declared by the instructions sent into the software.<br>
 * The symbol table is the "bridge" between the software and the flight simulator.<br>
 * All declared variables will create Symbols that will be stored inside the HashMap for easy and fast Access.  
 * @param HashMap <String,Symbol> symTable.
 * @author Arik Regev
 * @author Amit Koren
 */
public class SymbolTable {
	private double returnValue = 0;
	private HashMap<String,Symbol> symTable;
	public final SimulatorCom simCom;
	
	public SymbolTable(String[] simPaths) {
		symTable = new HashMap<String, Symbol>();
		this.simCom = new SimulatorCom(simPaths);
	}
	public Symbol getSymbol(String s) throws Exceptions.SymbolNotExistException {
		if(!symTable.containsKey(s)) throw new Exceptions.SymbolNotExistException(s);
		return symTable.get(s);
	}
	public void addSymbol(String s, Symbol sym) throws Exceptions.SymbolAlreadyExistException{
		if(symTable.containsKey(s) && symTable.get(s).isInitialized()) throw new Exceptions.SymbolAlreadyExistException(s);
		if(symTable.containsKey(s) && !symTable.get(s).isInitialized())
			removeSymbol(s);
		if (sym == null)
			sym = new Symbol() {
				@Override
				public void setValue(double val) {
					RegularSymbol newSymbol = new RegularSymbol(s);
					newSymbol.setValue(val);
					removeSymbol(s);
					try {
						addSymbol(s, newSymbol);
					} catch (SymbolAlreadyExistException e) {}
				}
				@Override
				public double getValue() throws SymbolUnInitializedException {
					throw new SymbolUnInitializedException(s);
				}
				/**
				 * The method gives the ability in runtime to know if the variable is initialized or not.
				 * <br>Inside this class the method will always return false because if we encountered the method here the variable is not initialized.
				 * <br>Example for an uninitialized variable:
				 * <pre>var x</pre>
				 * <br>Example for an initialized variable:
				 * <pre>var x = 5</pre>
				 * <pre>var y = bind /path</pre>
				 */
				@Override
				public boolean isInitialized() {
					return false;
				}
		};
		
		symTable.put(s, sym);
	}
	private void removeSymbol(String s) {
		symTable.remove(s);
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
