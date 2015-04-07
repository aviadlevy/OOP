package oop.ex6.filescript.sections.filters;

import java.io.File;

public class FileWritable extends YesOrNoFilterParent implements Filter {

	/**
	 * if YES, trueOrFalse is true. if NO trueOrFalse is false.
	 * 
	 * @param yesNo the string of YES \ NO
	 * @throws Warning 
	 */
	public FileWritable(String yesNo) throws WarningYesOrNoException {
		super(yesNo);
	}
	
	@Override
	public boolean isPass(File file) {
		return (file.canWrite() == trueOrFalse);
	}
}
