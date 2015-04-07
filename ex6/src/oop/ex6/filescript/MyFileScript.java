package oop.ex6.filescript;

import java.io.File;
import java.util.ArrayList;

import oop.ex6.filescript.sections.Section;

public class MyFileScript {

	/**
	 * The manager that run the program
	 * 
	 * @param args string array with 2 arguments. the first is the directory
	 * where all the files we need to filter and sort is in, and the 2nd is the
	 * command file. 
	 */
	public static void main(String[] args) {
		try {
			File directory = new File(args[0]);
			File[] listOfFiles = directory.listFiles();
			File[] sortFile;
			//send the command file to parsing.
			ArrayList<Section> sections = Parsing.parseFile(new File(args[1]));
			//check if warning is found in this section
			for(int i=0; i<sections.size(); i++) {
				for(int k=0; k<sections.get(i).getWarnings().length; k++) {
					if(sections.get(i).getWarnings()[k] != 0) {
						System.out.println("Warning in line "+
								String.valueOf(sections.get(i).getWarnings()[k]));
					}
				}
				//send all files to sorting
				sortFile = sections.get(i).getOrder().sortFile(listOfFiles);
				//check if file pass filter, if yes print
				for(int j=0; j<sortFile.length; j++) {
					if(sortFile[j].isFile()) {
						if(sections.get(i).getFilter().isPass(sortFile[j])) {
							System.out.println(sortFile[j].getName());
						}
					}
				}
			}
		} catch (FatalErrorException e) {
			System.err.println(e.getMessage());
		} 

	}

}
