package oop.ex6.filescript.sections.orders;

import java.io.File;

public class ReverseOrder implements Order {

	private Order orderToReverse;
		
	public ReverseOrder(Order reverse) {
		this.orderToReverse = reverse;
	}
	
	/**
	 * @param files the files we sorting.
	 */
	@Override
	public File[] sortFile(File[] files) {
		File[] reverse = new File[files.length];
		files = this.orderToReverse.sortFile(files);
		//reverse the order of the files.
		for(int i=0; i<files.length;i++) {
			reverse[i] = files[files.length - i - 1];
		}		
		return reverse;
	}
}
