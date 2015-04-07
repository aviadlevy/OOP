package oop.ex7.main.Variable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import oop.ex7.main.RegExPatterns;

public class ToArray implements Variable {
	private static final int ARRAY_VALUES = 1;
	private static final String SPLIT_PER_COMMA = ",";
	private final Variable _variable;
	
	/**
	 * Constructor.
	 * @param var variable to work on.
	 */
	public ToArray(Variable var) {
		_variable = var;
	}
	
	/**
	 * @return variable of array's kind.
	 */
	public Variable getVar() {
		return _variable;
	}

	@Override
	public String getName() {
		return _variable.getName();
	}

	@Override
	public int getLevel() {
		return _variable.getLevel();
	}
	
	@Override
	public String getValue() {
		return _variable.getValue();
	}
	
	@Override
	public Pattern getPattern() {
		return _variable.getPattern();
	}

	@Override
	public boolean isValid(String valueToCheck) throws VariableErrorException {
		GeneralCheck check = new GeneralCheck(_variable.getPattern());

		if (valueToCheck == null)
			return true;

		try {
			Matcher m = RegExPatterns.INSTANCE_ARRAY_INSERT
					.matcher(valueToCheck);
			m.matches();
			String s = m.group(ARRAY_VALUES) + " "; // to avoid cases such as
													// {5,}.
			String[] str = s.split(SPLIT_PER_COMMA);
		
			boolean[] valsToReturn = new boolean[str.length];
			for (int i = 0; i < valsToReturn.length; i++)
				valsToReturn[i] = false;

			if (str.length == 1 && str[0].equals(" "))
				return true;

			for (int i = 0; i < str.length; i++) {
				try {
					if (check.checkExecute(str[i], _variable))
						valsToReturn[i] = true;
				} catch (VariableErrorException e) {
					throw new VariableErrorException();
				}
			}

			for (boolean boolVal : valsToReturn)
				if (!boolVal)
					return false;
			return true;

		} catch (IllegalStateException e) { // for error in initializing
			return false;
		} catch (VariableErrorException e) {
			// TODO Auto-generated catch block
			throw new VariableErrorException();
		}
	}
}