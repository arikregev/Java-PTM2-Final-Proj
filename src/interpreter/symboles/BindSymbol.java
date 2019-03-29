package interpreter.symboles;

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

}