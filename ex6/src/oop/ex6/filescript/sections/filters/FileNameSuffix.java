package oop.ex6.filescript.sections.filters;

import java.io.File;

public class FileNameSuffix implements Filter {

	private String suffix;
	
	public FileNameSuffix(String suff) {
		this.suffix = suff;
	}

	/**
	 * @param file the file we need to check if pass the filter.
	 */
	@Override
	public boolean isPass(File file) {
		return file.getName().endsWith(suffix);
	}
}
