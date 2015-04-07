package oop.ex7.main.Variable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import oop.ex7.main.Parser;
import oop.ex7.main.RegExPatterns;

public class VariableFactory {
	private final static int VAR_TYPE = 1, VAR_NAME = 3, VALUE = 5,
			IS_ARRAY = 2;
	private static final String INT = "int", DOUBLE = "double",
			STRING = "String", CHAR = "char", BOOLEAN = "boolean";

	/**
	 * Variables creator.
	 * @param line variable line in code.
	 * @return a new variable object.
	 * @throws VariableErrorException
	 */
	public static Variable createVariable(String line)
			throws VariableErrorException {
		Matcher m = RegExPatterns.VARIABLE_GENERAL_PATTERN.matcher(line);
		m.matches();
		String type = m.group(VAR_TYPE);
		String name = m.group(VAR_NAME);
		String value = m.group(VALUE);
		String isArray = m.group(IS_ARRAY);

		Variable newVariable = null;

		switch (type) {
		case INT:
			newVariable = new IntegerVariable(name, value, Parser.currentLevel);
			break;
		case DOUBLE:
			newVariable = new DoubleVariable(name, value, Parser.currentLevel);
			break;
		case STRING:
			newVariable = new StringVariable(name, value, Parser.currentLevel);
			break;
		case CHAR:
			newVariable = new CharVariable(name, value, Parser.currentLevel);
			break;
		case BOOLEAN:
			newVariable = new BooleanVariable(name, value, Parser.currentLevel);
			break;
		default:
			throw new VariableErrorException();
		}

		// array decorator.
		if (isArray != null) {
			newVariable = new ToArray(newVariable);
		}

		// value validation:
		if (!newVariable.isValid(newVariable.getValue()))
			throw new VariableErrorException();
		// name validation:
		if (!checkNameValidation(name))
			throw new VariableErrorException();
		// if name of variable is already exist:
		if (occupiedName(name))
			throw new VariableErrorException();

		return newVariable;
	}

	/**
	 * This method checks if variable name is already exist.
	 * @param name name of variable to check.
	 * @return true if name is taken.
	 */
	private static boolean occupiedName(String name) {
		if (Parser.variables != null)
			for (Variable var : Parser.variables)
				if (var.getName().equals(name))
					return true;
		if (Parser.argsOfFunc != null)
			for (Variable var : Parser.argsOfFunc)
				if (var.getName().equals(name))
					return true;
		return false;
	}

	/**
	 * This method checks if variable's name is legal.
	 * @param name name to check.
	 * @return true if name is legal.
	 */
	private static boolean checkNameValidation(String name) {
		return Pattern.compile(RegExPatterns.VAR_NAME).matcher(name).matches();
	}
}