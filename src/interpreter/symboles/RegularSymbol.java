package interpreter.symboles;

public class RegularSymbol implements Symbol {

	private double value;

	public RegularSymbol(double value) {
		this.value = value;
	}

	@Override
	public void setValue(double val) {
		this.value = val;
	}

	@Override
	public double getValue() {
		return this.value;
	}

}
