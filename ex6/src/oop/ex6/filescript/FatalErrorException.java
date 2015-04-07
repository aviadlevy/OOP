package oop.ex6.filescript;

public class FatalErrorException extends Exception {
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructor with massage as instructed.
	 */
	public FatalErrorException() {
		super("ERROR");
	}
}
