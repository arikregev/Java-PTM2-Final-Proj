package interpreter.symboles;
/**
 * Symbol Interface, will represent variables declared by the code received.<br>
 * Every Symbol created will be inserted into the Symbol table for control.
 * @author Arik Regev
 *
 */
public interface Symbol {
	public void setValue(double val);
	public double getValue();
}
