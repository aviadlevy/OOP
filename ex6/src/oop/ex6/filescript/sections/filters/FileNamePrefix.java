package oop.ex6.filescript.sections.filters;

import java.io.File;

public class FileNamePrefix implements Filter {

	private String preffix;
	
	public FileNamePrefix(String pref) {
		this.preffix = pref;
	}

	/**
	 * @param file the file we need to check if pass the filter.
	 */
	@Override
	public boolean isPass(File file) {
		return file.getName().startsWith(preffix);
	}

}
