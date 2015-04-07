package oop.ex7.main.Variable;
import java.util.regex.Pattern;

import oop.ex7.main.RegExPatterns;

public class BooleanVariable implements Variable {
	private String _variableName, _variableValue;
	private int _level;
	
	/**
	 * Default constructor.
	 */
	public BooleanVariable() {}

	/**
	 * Boolean initialized variable constructor.
	 * @param name variable name.
	 * @param value variable value.
	 * @param level variable scope level.
	 */
	public BooleanVariable(String name, String value, int level) {
		_variableName = name;
		_variableValue = value;
		_level = level;
	}

	/**
	 * Boolean variable declaration-only constructor.
	 * @param name variable name.
	 * @param level variable scope level.
	 */
	public BooleanVariable(String name, int level) {
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
		return RegExPatterns.BOOLEAN;
	}

	@Override
	public boolean isValid(String valueToCheck) throws VariableErrorException {
		GeneralCheck check = new GeneralCheck(RegExPatterns.BOOLEAN);
		return check.checkExecute(valueToCheck, this);
	}
}