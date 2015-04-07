package oop.ex7.main.Function;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import oop.ex7.main.RegExPatterns;
import oop.ex7.main.SyntaxErrorException;
import oop.ex7.main.Variable.*;

public class Function {
	private static final String INT = "int", DOUBLE = "double",
			STRING = "String", CHAR = "char", BOOLEAN = "boolean",
			VOID = "void", NONE = "", SPLIT_PER_COMMA = ",";
	
	private final String _funcName, _funcArgsString;
	private Variable _typeToReturn;
	private final static int VAR_TYPE = 1, IS_ARRAY = 2;

	/**
	 * Function constructor.
	 * 
	 * @param name function name.
	 * @param args function's arguments.
	 * @param typeToReturn return type of function.
	 * @throws SyntaxErrorException
	 */
	public Function(String name, String args, String typeToReturn)
			throws SyntaxErrorException {
		_funcName = name;
		_funcArgsString = args;
		_typeToReturn = convertToVariableClass(typeToReturn);
	}

	/**
	 * @return function name.
	 */
	public String getName() {
		return _funcName;
	}

	/**
	 * @return function return type.
	 */
	public Variable getReturnType() {
		return _typeToReturn;
	}

	/**
	 * @return function's arguments.
	 */
	public String getArgs() {
		return _funcArgsString;
	}

	/**
	 * This method converts function's arguments to "real" variables in the
	 * program.
	 * 
	 * @param argString function's arguments line.
	 * @return an array, containing the processed new variables.
	 * @throws SyntaxErrorException
	 */
	public static ArrayList<Variable> convertArgsToVariables(String argString)
			throws SyntaxErrorException {
		if (argString.equals(NONE))
			return null;
		String[] var = argString.split(SPLIT_PER_COMMA);
		ArrayList<Variable> variablesAfterDiagnose = new ArrayList<Variable>();
		for (int i = 0; i < var.length; i++) {
			variablesAfterDiagnose.add(VariableFactory.createVariable(var[i]
					+ ";"));
			for (int j = 0; j < variablesAfterDiagnose.size() - 1; j++) {
				if (variablesAfterDiagnose.get(j).getName().equals(
						variablesAfterDiagnose.get(variablesAfterDiagnose.size()
								- 1).getName()))
					throw new FunctionErrorException();
			}
		}
		return variablesAfterDiagnose;
	}

	/**
	 * This method converts the return type from the original java syntax to
	 * program's variable's class name, in order to make it easier for us to
	 * check it later in the parsing operation.
	 * 
	 * @param valueToConvert return type value.
	 * @return string of variable class name.
	 * @throws ReturnValueException
	 */
	private Variable convertToVariableClass(String valueToConvert)
			throws ReturnValueException {
		Variable newDefinition = null;
		String s = "^\\s*(" + RegExPatterns.RETURN_TYPES + ")(\\[\\])?";
		Matcher m = Pattern.compile(s).matcher(valueToConvert);
		m.matches();
		String type = m.group(VAR_TYPE);
		String isArray = m.group(IS_ARRAY);
		
		switch (type) {
		case INT:
			newDefinition = new IntegerVariable();
			break;
		case DOUBLE:
			newDefinition = new DoubleVariable();
			break;
		case STRING:
			newDefinition = new StringVariable();
			break;
		case CHAR:
			newDefinition = new CharVariable();
			break;
		case BOOLEAN:
			newDefinition = new BooleanVariable();
			break;
		case VOID:
			newDefinition = new ReturnVoid();
			break;
		default:
			throw new ReturnValueException();
		}
		
		// array decorator.
		if (isArray != null)
			newDefinition = new ToArray(newDefinition);
		
		return newDefinition;
	}
}