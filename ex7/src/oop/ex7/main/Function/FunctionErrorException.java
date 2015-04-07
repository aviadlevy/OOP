package oop.ex7.main.Function;
import oop.ex7.main.SyntaxErrorException;

public class FunctionErrorException extends SyntaxErrorException {
	private static final long serialVersionUID = 1L;

	/**
	 * Functions' errors exception.
	 */
	public FunctionErrorException() {
		super("Function error has occurred.");
	}
}