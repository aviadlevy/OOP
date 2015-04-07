package oop.ex7.main.Variable;
import oop.ex7.main.SyntaxErrorException;

public class VariableErrorException extends SyntaxErrorException {
	private static final long serialVersionUID = 1L;

	/**
	 * Variables' error exception.
	 */
	public VariableErrorException() {
		super("Variable error has occurred.");
	}

	public VariableErrorException(String message) {
		super(message);
	}
}