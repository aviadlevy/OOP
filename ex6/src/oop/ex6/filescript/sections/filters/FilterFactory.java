package oop.ex6.filescript.sections.filters;

import oop.ex6.filescript.WarningException;

public class FilterFactory {
	
	private static final String SEPEATOR = "#",	NOT = "NOT";
	
	/**
	 * @param filterString string we need to convert to Filter.
	 * @return the Filter the string represent.
	 * @throws Warning 
	 */
	public static Filter createFilter(String filterString) throws WarningException {
		boolean isNegative = false;
		Filter filter = null;
		
		String[] array = filterString.split(SEPEATOR); //split the string with #

		//check if NOT in the end of the string
		if(array[array.length - 1].equals(NOT))
			isNegative = true;
		
		//check all cases given and send object according to what needed.
		switch (array[0]) {
			case "greater_than":
				filter = new FileGreaterThan(Double.valueOf(array[1]));
				break;
			case "smaller_than":
				filter = new FileSmallerThan(Double.valueOf(array[1]));
				break;
			case "between":
				//check if the first value is smaller. if not filter remain null.
				try {
					filter = new FileBetween(Double.valueOf(array[1]),
							Double.valueOf(array[2]));
				} catch (WarningBetweenVariablesException e) {
					throw new WarningException();
				}
				break;
			case "file":
				filter = new FileNameEquals( array[1]);
				break;
			case "contains":
				filter = new FileNameContains( array[1]);
				break;
			case "prefix":
				filter = new FileNamePrefix( array[1]);
				break;
			case "suffix":
				filter = new FileNameSuffix( array[1]);
				break;
			case "writable":
				//check if YES / NO is spelled correctly
				try {
					filter = new FileWritable( array[1]);
				} catch (WarningYesOrNoException e) {
					throw new WarningException();
				}
				break;
			case "executable":
				//check if YES / NO is spelled correctly
				try {
					filter = new FileExecutable( array[1]);
				} catch (WarningYesOrNoException e) {
					throw new WarningException();
				}
				break;
			case "hidden":
				//check if YES / NO is spelled correctly
				try {
					filter = new FileHidden( array[1]);
				} catch (WarningYesOrNoException e) {
					throw new WarningException();
				}
				break;
			case "all":
				filter = new FileAll();
				break;
			default:
				throw new WarningException();
		}
		
		if(isNegative && filter != null)
			filter = new NegFilter(filter);
		
		return filter;
	}
}
