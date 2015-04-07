package oop.ex7.main.Variable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import oop.ex7.main.Parser;
import oop.ex7.main.RegExPatterns;
import oop.ex7.main.Function.ReturnValueException;

public class GeneralCheck {
	private static final String NONE = "", NEGATIVE_SIGN = "-", DOT = "\\.",
	RETURN_VOID_CLASS = "oop.ex7.main.Function.ReturnVoid";
	private Pattern _pattern;
	private final int NO_VALUES = 0;

	/**
	 * Constructor.
	 * @param p regex pattern.
	 */
	public GeneralCheck(Pattern p) {
		_pattern = p;
	}

	/**
	 * A general check.
	 * @param valueToCheck value to be checked.
	 * @param thisVar the current variable to check for.
	 * @return true if validation was ok.
	 * @throws VariableErrorException
	 */
	public boolean checkExecute(String valueToCheck, Variable thisVar)
			throws VariableErrorException {
		if (valueToCheck == null)
			return true;

		if (valueToCheck.equals(NONE)
				&& thisVar.getClass().getName().equals(RETURN_VOID_CLASS))
			return true;
		
		else if (valueToCheck.equals(NONE)
				|| thisVar.getClass().getName().equals(RETURN_VOID_CLASS))
			return false;
		
		// variable as return value:
		valueToCheck = valueToCheck.replaceAll("\\s+", "");
		String[] valAfterSplit = valueToCheck.split(RegExPatterns.OPERATORS);

		// (return's unwanted values) / no-operands check:
		if (valAfterSplit.length == NO_VALUES)
			throw new ReturnValueException(); // has to be ReturnValueException

		boolean[] valsToReturn = new boolean[valAfterSplit.length];
		for (int i = 0; i < valsToReturn.length; i++)
			valsToReturn[i] = false;
		int i = 0;
		boolean found;
		for (String val : valAfterSplit) {
			found = false;
			// check if variable exist:
			if (Parser.variables != null) {
				for (Variable variable : Parser.variables) {
					if (variable.getName().equals(val)) {
						if (variable.getClass().equals(thisVar.getClass()) ||
						// specialty of Double
								(variable instanceof IntegerVariable && thisVar 
										instanceof DoubleVariable)) {
							if (variable.getValue() != null) {
								valsToReturn[i] = true;
								i++;
								found = true;
								break;
							}
						} else
							throw new VariableErrorException();
					}
				}
			}
			if (found)
				continue;
			// if variable is a function's argument:
			if (Parser.argsOfFunc != null) {
				for (Variable argsVar : Parser.argsOfFunc) {
					if (argsVar.getName().equals(val)) {
						if (argsVar.getClass().equals(thisVar.getClass()) ||
						// specialty of Double
								(argsVar instanceof IntegerVariable && thisVar 
										instanceof DoubleVariable)) {
							valsToReturn[i] = true;
							i++;
							found = true;
							break;
						} else
							throw new VariableErrorException();
					}
				}
			}
			if (found)
				continue;
			
			// negative number case check:
			if (i == 0 && val.equals(NONE) 
					&& valueToCheck.startsWith(NEGATIVE_SIGN)) {
				valsToReturn[i] = true;
				i++;
				found = true;
			}
			if (found)
				continue;
			
			// check for a primitive value:
			if (i < valAfterSplit.length) { // to avoid over-leaking from array:
				Matcher m = _pattern.matcher(val);
				valsToReturn[i] = m.matches();
				i++;
			}
		}

		// final validation check:
		for (boolean boolVal : valsToReturn)
			if (!boolVal)
				return false;
		return true;
	}
	
	/**
	 * This method purpose is to validate the given array's index.
	 * @param arrayIdx array's index to check.
	 * @return true if array's index is legal.
	 */
	public static boolean arrayIndexValidation(String arrayIdx) {
		if (arrayIdx != null) {
			// fractional number case:
			if (arrayIdx.split(DOT).length > 1)
				return false;
			
			// checks mathematical expressions:
			Matcher m = RegExPatterns.LEGAL_MATH_EXPRESSION.matcher(arrayIdx);
			if (m.matches())
				return true;
			m = RegExPatterns.ILLEGAL_MATH_EXPRESSION.matcher(arrayIdx);
			if (m.matches())
				return false;
		}
		return true;
	}
}