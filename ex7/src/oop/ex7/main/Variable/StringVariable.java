package oop.ex7.main.Variable;
import java.util.regex.Pattern;
import oop.ex7.main.RegExPatterns;

public class StringVariable implements Variable {
	private String _variableName, _variableValue;
	private int _level;

	/**
	 * Default constructor.
	 */
	public StringVariable() {}

	/**
	 * String initialized variable constructor.
	 * @param name variable name.
	 * @param value variable value.
	 * @param level variable scope level.
	 */
	public StringVariable(String name, String value, int level) {
		_variableName = name;
		_variableValue = value;
		_level = level;
	}

	/**
	 * String variable declaration-only constructor.
	 * @param name variable name.
	 * @param level variable scope level.
	 */
	public StringVariable(String name, int level) {
		_variableName = name;
		_variableValue = null;
		_level = level;
	}

	@Override
	public String getName() {
		return _variableName;
	}

	@Override
	public String getValue() {
		return _variableValue;
	}

	@Override
	public int getLevel() {
		return _level;
	}
	
	@Override
	public Pattern getPattern() {
		return RegExPatterns.STRING;
	}

	@Override
	public boolean isValid(String valueToCheck) throws VariableErrorException {
		GeneralCheck check = new GeneralCheck(RegExPatterns.STRING);
		return check.checkExecute(valueToCheck, this);
	}
}