package interpreter.symboles;
/**
 * Regular Symbol represents variables that are numeric based like int, double. 
 * <br>
 * @param Double
 * @author Arik Regev
 * @author Amit Koren
 */
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
