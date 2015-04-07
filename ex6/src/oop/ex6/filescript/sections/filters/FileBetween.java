package oop.ex6.filescript.sections.filters;

import java.io.File;

public class FileBetween implements Filter {
	
	private final static int CONVERT_BYTES = 1024;
	private double maxLimit, minLimit;
	
	public FileBetween(double firstNumber, double secondNumer) throws WarningBetweenVariablesException {
		if(firstNumber >= secondNumer)
			throw new WarningBetweenVariablesException();
		this.minLimit = firstNumber;
		this.maxLimit = secondNumer;
	}
	
	/** 
	 * @param file the file we need to check if pass the filter.
	 */
	@Override
	public boolean isPass(File file) {
		return ((file.length() / CONVERT_BYTES) >= minLimit) && 
				((file.length() / CONVERT_BYTES) <= maxLimit);
	}

}
