package oop.ex7.main.Function;
import oop.ex7.main.Variable.VariableErrorException;

public class ReturnValueException extends VariableErrorException {
	private static final long serialVersionUID = 1L;
	
	/**
	 * Return values' error exception.
	 */
	public ReturnValueException() {
		super("Error in return line.");
	}
}