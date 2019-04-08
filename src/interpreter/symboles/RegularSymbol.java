package interpreter.symboles;

import interpreter.expression.NumberExpression;

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
}
