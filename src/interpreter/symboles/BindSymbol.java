package interpreter.symboles;
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
	private String path;
	private SymbolTable symTable;
	
	public BindSymbol(String path, SymbolTable symTable) {
		this.path = path;
		this.symTable = symTable;
	}
	@Override
	public void setValue(double val) {
		// TODO Auto-generated method stub

	}

	@Override
	public double getValue() {
		// TODO Auto-generated method stub
		return 0;
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

}