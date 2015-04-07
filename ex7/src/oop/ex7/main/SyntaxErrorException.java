package oop.ex7.main;

public class SyntaxErrorException extends Exception {
	private static final long serialVersionUID = 1L;
	
	/**
	 * A general syntax error exception. The private-cases other exceptions are
	 * extends it.
	 */
	public SyntaxErrorException() {
		super("General syntax error. Improve your code!");
	}
	
	/**
	 * Get the error message from children-exceptions.
	 * @param message
	 */
	public SyntaxErrorException(String message) {
		super(message);
	}
}
