package oop.ex6.filescript.sections.filters;

import java.io.File;

public class FileExecutable extends YesOrNoFilterParent implements Filter {
		
	/**
	 * if YES, trueOrFalse is true. if NO trueOrFalse is false.
	 * 
	 * @param yesNo the string of YES \ NO
	 * @throws Warning 
	 */
	public FileExecutable(String yesNo) throws WarningYesOrNoException {
		super(yesNo);
	}
	
	/** 
	 * @param file the file we need to check if pass the filter.
	 */
	@Override
	public boolean isPass(File file) {
		return (file.canExecute() == trueOrFalse);	
	}
}
