package oop.ex7.main.Variable;
import java.util.regex.Pattern;

public interface Variable {
	/**
	 * @return variable name.
	 */
	public String getName();
	
	/**
	 * @return variable value.
	 */
	public String getValue();
	
	/**
	 * @return variable scope level.
	 */
	public int getLevel();

	/**
	 * @return regex pattern of variable.
	 */
	public Pattern getPattern();

	/**
	 * Validation check.
	 * (We added the value we want to check as an argument in order to help
	 * the decorator.)
	 * @param valueToCheck value to check.
	 * @return true if value is valid.
	 * @throws VariableErrorException
	 */
	public boolean isValid(String valueToCheck) throws VariableErrorException;
}