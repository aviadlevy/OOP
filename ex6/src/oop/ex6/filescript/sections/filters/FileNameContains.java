package oop.ex6.filescript.sections.filters;

import java.io.File;

public class FileNameContains implements Filter {
	
	private String nameContains;
	
	public FileNameContains(String name) {
		this.nameContains = name;
	}

	/**
	 * @param file the file we need to check if pass the filter.
	 */
	@Override
	public boolean isPass(File file) {
		return file.getName().contains(nameContains);
	}

}
