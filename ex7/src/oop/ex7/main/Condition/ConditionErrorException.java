package oop.ex7.main.Condition;
import oop.ex7.main.SyntaxErrorException;

public class ConditionErrorException extends SyntaxErrorException {
	private static final long serialVersionUID = 1L;

	/**
	 * If / while conditions' error exception.
	 */
	public ConditionErrorException() {
		super("Error in condition statement.");
	}
}