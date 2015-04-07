package oop.ex6.filescript.sections.orders;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;

public class TypeOrder implements Order {

	private static final char DOT = '.';
	
	/**
	 * @param files the array of files to sort.
	 * @return Comparator that sort by type.
	 */
	public static Comparator<File> sortByType(File[] files) {
		Comparator<File> comparator = new Comparator<File>() {

			@Override
			public int compare(File o1, File o2) {
				return (getType(o1).compareTo(getType(o2)));
			}
		};
		return comparator;
	}
	
	/**
	 * @param name file we want its type.
	 * @return the type of the file
	 */
	private static String getType(File name) {
		String type = "";
		if(!name.getName().endsWith(Character.toString(DOT))) { //check if no ending, just dot
			//run from the end of the file name until we meet '.'
			for(int i=name.getName().length() - 1; i >= 0; i--) {
				type = String.valueOf(name.getName().charAt(i)) + type; 
				if(name.getName().charAt(i) == DOT)
					break;
			}
		}
		return type;
	}
	
	/**
	 * first sort Alphabetic, then by type.
	 * 
	 * @param files the files we sorting.
	 */
	@Override
	public File[] sortFile(File[] files) {
		Arrays.sort(files, AbsoluteOrder.sortByName(files));
		Arrays.sort(files,sortByType(files));
		return files;
	}
}
