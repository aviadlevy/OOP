package oop.ex7.main.Condition;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import oop.ex7.main.Parser;
import oop.ex7.main.RegExPatterns;
import oop.ex7.main.Function.Function;
import oop.ex7.main.Variable.*;

public class Condition {
	private final static int VAR_NAME = 1;
	private final static String TRUE = "true", FALSE = "false";

	/**
	 * A class contains conditions if and while check.
	 * 
	 * @param condition current condition to check.
	 * @return true if checking was well.
	 */
	public static boolean checkCondition(String condition) {
		// in true / false conditions case:
		if (condition.equals(TRUE) || condition.equals(FALSE))
			return true;
		
		// other condition's cases:
		Pattern c = RegExPatterns.CONDITION_LINE;
		try {
			Matcher m = c.matcher(condition);
			m.matches();
			String tempName = m.group(VAR_NAME);
			for (Variable var : Parser.variables) {
				if (var.getName().equals(tempName))
					if (var instanceof BooleanVariable) {
						if (var.getValue() != null)
							return true;
					} else if (var instanceof ToArray)
						if (((ToArray) var).getVar() instanceof BooleanVariable)
							if (var.getValue() != null)
								return true;
			}
		} catch (IllegalStateException e) { // at initializing error
			return false;
		}

		// in case of function return value as a condition:
		for (Function func : Parser.functions)
			if (func.getName().equals(condition))
				if (func.getReturnType().equals("boolean"))
					return true;
		return false;
	}
}