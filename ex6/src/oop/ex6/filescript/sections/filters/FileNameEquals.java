package oop.ex6.filescript.sections.filters;

import java.io.File;

public class FileNameEquals implements Filter {
	
	private String nameEquals;
	
	public FileNameEquals(String name) {
		this.nameEquals = name;
	}

	/**
	 * @param file the file we need to check if pass the filter.
	 */
	@Override
	public boolean isPass(File file) {
		return nameEquals.equals(file.getName());
	}

}
