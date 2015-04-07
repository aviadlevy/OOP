package oop.ex6.filescript;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import oop.ex6.filescript.sections.*;
import oop.ex6.filescript.sections.filters.*;
import oop.ex6.filescript.sections.orders.*;


public class Parsing{
    
	private static ArrayList<Section> sections;
	private static Scanner scanner;
	private static final int LINE_IN_SECTION = 4;
	private static final String FILTER = "FILTER", ORDER = "ORDER";
	
	
	/**
	 * @param commandFile The file contain sections we need to parse.
	 * @return sections after parsing.
	 * @throws FatalError in case of Type I Error.
	 */
	public static ArrayList<Section> parseFile(File commandFile) throws FatalErrorException {
		try {
			sections  = new ArrayList<Section>();
			Filter filter = null;
			Order order;
			String line;
			int[] warnings = new int[2]; //Initialize to 2 cells, so if any Type II
										 //error happens, keep its line in matched cell.

			//we'll use in case we skipped line (FILTER right after ORDER)
			int lineSkipped = 0;
			
			scanner = new Scanner(commandFile);
			//run until no next line and no next section.
			for(int lineNumber=1; scanner.hasNext() || 
					lineNumber%LINE_IN_SECTION != 1; lineNumber++) {
				if(scanner.hasNext()) {
					line = scanner.next();
				} else { 	//in case of empty line in last section
					line = "";
				}
				//check what part of section line we're in
				switch (lineNumber % LINE_IN_SECTION) {
					case 1:
						if(!line.equals(FILTER))
							throw new FatalErrorException();
						break;
					case 2:
						try {
							filter = FilterFactory.createFilter(line);
						} catch (WarningException e) {
							warnings[0] = lineNumber - lineSkipped;
							filter = new FileAll();
						}
						break;
					case 3:
						if(!line.equals(ORDER))
							throw new FatalErrorException();
						break;
					case 0:
						try{
							order = OrderFactory.createOrder(line);
						} catch(WarningException e) {
							order = new AbsoluteOrder();
							//check if no line after order
							if(line.equals(FILTER)) {
								lineNumber++;  //so we'll not confuse the sections lines
								lineSkipped++;  //let's remember we skipped line
							} else{  //if no FILTER that mean Type II error happened
								warnings[1] = lineNumber - lineSkipped;
							}
						}
						sections.add(new Section(filter,order,warnings));
						warnings = new int[2];
						break;
				}
			}
		
			return sections;
		} catch (FileNotFoundException e) {
			throw new FatalErrorException();
		}
	}
}
