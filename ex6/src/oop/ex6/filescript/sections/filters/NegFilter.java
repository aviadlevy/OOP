package oop.ex6.filescript.sections.filters;

import java.io.File;

public class NegFilter implements Filter {
	
	private Filter filterToNegation;
	
	public NegFilter(Filter filterToNeg) {
		this.filterToNegation = filterToNeg;
	}

	/**
	 * return the opposite of the filter we need to negation.
	 * 
	 * @param file the file we need to check if pass the filter
	 */
	@Override
	public boolean isPass(File file) {
		return !this.filterToNegation.isPass(file);
	}

}
