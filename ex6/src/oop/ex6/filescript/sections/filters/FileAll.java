package oop.ex6.filescript.sections.filters;

import java.io.File;

public class FileAll implements Filter {

	/**
	 * every file is passing, so always return true.
	 * 
	 * @param file the file we need to check if pass the filter
	 */
	@Override
	public boolean isPass(File file) {
		return true;
	}

}
