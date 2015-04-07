package oop.ex6.filescript.sections.orders;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;

public class SizeOrder implements Order {

	/**
	 * @param files the array of files to sort.
	 * @return Comparator that sort by size.
	 */
	public static Comparator<File> sortBySize(File[] files) {
		Comparator<File> comparator = new Comparator<File>() {

			@Override
			public int compare(File o1, File o2) {
				if(o1.length() - o2.length() > 0)
					return 1;
				else if(o1.length() - o2.length() < 0)
					return -1;
				else
					return 0;
			}
		};
		return comparator;
	}
	
	/**
	 * first sort Alphabetic, then by size.
	 * 
	 * @param files the files we sorting.
	 */
	@Override
	public File[] sortFile(File[] files) {
		Arrays.sort(files, AbsoluteOrder.sortByName(files));
		Arrays.sort(files,sortBySize(files));
		return files;
	}
}
