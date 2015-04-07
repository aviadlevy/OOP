package oop.ex6.filescript.sections.orders;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;

public class AbsoluteOrder implements Order {

	/**
	 * @param files the array of files to sort.
	 * @return Comparator that sort by name.
	 */
	public static Comparator<File> sortByName(File[] files) {
		Comparator<File> comparator = new Comparator<File>() {

			@Override
			public int compare(File o1, File o2) {
				return (o1.getName().compareTo(o2.getName()));
			}
		};
		return comparator;
	}
	
	/**
	 * @param files the files we sorting.
	 */
	@Override
	public File[] sortFile(File[] files) {
		Arrays.sort(files,sortByName(files));
		return files;
	}
}
