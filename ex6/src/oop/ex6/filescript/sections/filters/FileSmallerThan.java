package oop.ex6.filescript.sections.filters;

import java.io.File;

public class FileSmallerThan implements Filter {
	
	private final static int CONVERT_BYTES = 1024;
	private double maximumLimit;
	
	public FileSmallerThan(double number) {
		this.maximumLimit = number;
	}

	/** 
	 * @param file the file we need to check if pass the filter.
	 */
	@Override
	public boolean isPass(File file) {
		return (file.length() / CONVERT_BYTES) < maximumLimit;
	}

}
