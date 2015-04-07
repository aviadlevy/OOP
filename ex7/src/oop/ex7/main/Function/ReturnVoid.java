package oop.ex7.main.Function;
import java.util.regex.Pattern;
import oop.ex7.main.Variable.Variable;

/**
 * A helper class that contains all parameters and properties of a
 * void function return command.
 */
public class ReturnVoid implements Variable {
	private static final String NONE = "";

	@Override
	public String getName() {
		return null;
	}

	@Override
	public String getValue() {
		return null;
	}

	@Override
	public int getLevel() {
		return 0;
	}

	@Override
	public Pattern getPattern() {
		return null;
	}

	@Override
	public boolean isValid(String valueToCheck) {
		if(valueToCheck.equals(NONE))
			return true;
		return false;
	}
}