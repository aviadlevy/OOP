package oop.ex6.filescript.sections.filters;

import java.io.File;

public class FileGreaterThan implements Filter{
	
	private final static double CONVERT_BYTES = 1024;
	private double minimumLimit;
	
	public FileGreaterThan(double number) {
		this.minimumLimit = number;
	}
	
	/** 
	 * @param file the file we need to check if pass the filter.
	 */
	@Override
	public boolean isPass(File file) {
		return (file.length() / CONVERT_BYTES) > minimumLimit;
	}
	
	

}
