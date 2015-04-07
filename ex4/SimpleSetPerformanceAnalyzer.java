import java.util.*;

/**
 * has a main method that measures the run-times requested in the "Performance 
 * Analysis" section. Implement it as you wish.
 * 
 * @author Aviad Levy
 *
 */
public class SimpleSetPerformanceAnalyzer {

	public static void main(String[] args) {

		SimpleSet[] listOfSets = { new ChainedHashSet(), new OpenHashSet(),
				new CollectionFacadeSet(new TreeSet<String>()),
				new CollectionFacadeSet(new LinkedList<String>()),
				new CollectionFacadeSet(new HashSet<String>()) };

		SimpleSet[] listOfSetsData2 = { new ChainedHashSet(),
				new OpenHashSet(),
				new CollectionFacadeSet(new TreeSet<String>()),
				new CollectionFacadeSet(new LinkedList<String>()),
				new CollectionFacadeSet(new HashSet<String>()) };
		
		String[] setsName = { "ChainedHashSet", "OpenHashSet", "TreeSet",
				"LinkedList", "HashSet" };

		int[] resultsData1 = new int[5];
		int[] resultsData2 = new int[5];
		
		int[] resultshi = new int[5];
		int[] resultsnumber = new int[5];


		String[] data1 = Ex4Utils
				.file2array("C:/Users/win7/Dropbox/μιξεγιν/OOP/ex4/data1.txt");

		String[] data2 = Ex4Utils
				.file2array("C:/Users/win7/Dropbox/μιξεγιν/OOP/ex4/data2.txt");

		System.out.println("initializing data 1: \n");
		// add all data1 to the structures
		for (int i = 0; i < 5; i++) {

			long timeBefore = new Date().getTime();
			for (String key : data1) {
				listOfSets[i].add(key);
			}
			long timeAfter = new Date().getTime();

			long difference = timeAfter - timeBefore;

			System.out.println(setsName[i] + " for data1 took " + (difference)
					+ " ml seconds");

			resultsData1[i] = (int) difference;
		}
		System.out.println("\ninitializing data 2: \n");
		// add all data2 to the structures
		for (int i = 0; i < 5; i++) {

			long timeBefore = new Date().getTime();
			for (String key : data2) {
				listOfSetsData2[i].add(key);
			}
			long timeAfter = new Date().getTime();

			long difference = timeAfter - timeBefore;

			System.out.println(setsName[i] + " for data 2 took " + (difference)
					+ " ml seconds");

			resultsData2[i] = (int) difference;
		}
		
		//best score data1:
		long bestScore = resultsData1[0];
		String bestSet = setsName[0];
		for (int i = 0; i < 5; i++) {
			if (resultsData1[i] < bestScore){
				bestScore = resultsData1[i];
				bestSet = setsName[i];
			}
		}
		
		bestScore = resultsData2[0];
		String bestSet2 =setsName[0];
		for (int i = 0; i < 5; i++) {
			if (resultsData2[i] < bestScore){
				bestScore = resultsData2[i];
				bestSet2 = setsName[i];
			}
		}
		
		System.out.println("\n"+bestSet+" is the fastest for data 1 initialazing and "
		+ bestSet2+ " is the fastest for data 2 initialazing\n");
		
		System.out.println("comparing data 1 and data 2:\n");
		// compare data 1 and data2
		for (int i = 0; i < 5; i++) {

			System.out.println("for the set : " + setsName[i] + " it took "
					+ resultsData1[i] + " ml for data 1. and "
					+ resultsData2[i] + " ml for data 2");

		}
		
		System.out.println("\nfinding hi in data 1 set:\n");
		// checks if contains hi after initilized by data 1
		for (int i = 0; i < 5; i++) {
			long timeBefore = new Date().getTime();
			listOfSets[i].contains("hi");
			long timeAfter = new Date().getTime();
			long difference = timeAfter - timeBefore;

			System.out.println("for the set " + setsName[i]
					+ " the time to find hi in data1 is " + difference);

			resultshi[i] = (int) difference;

		}
		
		//fastest for finding hi
		
		bestScore = resultshi[0];
		bestSet = setsName[0];
		for (int i = 0; i < 5; i++) {
			if (resultshi[i] < bestScore){
				bestScore = resultshi[i];
				bestSet = setsName[i];
			}
		}
		System.out.println("\n" + bestSet + " is the fastest for finding hi\n");

		
		
		System.out.println("\nfinding '-13170890158' in data1 set:\n");
		// checks if contains -13170890158 after initilized by data 1
		for (int i = 0; i < 5; i++) {
			long timeBefore = new Date().getTime();
			listOfSets[i].contains("-13170890158");
			long timeAfter = new Date().getTime();
			long difference = timeAfter - timeBefore;

			System.out.println("for the set " + setsName[i]
					+ " the time to find '-13170890158' in data1 is "
					+ (difference));

			resultsnumber[i] = (int) difference;

		}
		
		bestScore = resultsnumber[0];
		bestSet = setsName[0];
		for (int i = 0; i < 5; i++) {
			if (resultsnumber[i] < bestScore){
				bestScore = resultsnumber[i];
				bestSet  = setsName[i];
			}
		}
		
		System.out.println("\n" + bestSet + " is the fastest for finding '-13170890158' in data 1\n");


		System.out.println("\ncomparing finding hi and '-13170890158' in data1 set:\n");
		// compairs the time to find hi and -13170890158
		for (int i = 0; i < 5; i++) {
			System.out.println("for the set : " + setsName[i]
					+ " initialized by data 1. it took " + resultshi[i] + " ml to find hi and "
					+ resultsnumber[i] + " ml to find '-13170890158'");

		}
		
		System.out.println("\nfinding hi in data2 set:\n");
		// same when initiliazed by data 2
		for (int i = 0; i < 5; i++) {
			long timeBefore = new Date().getTime();
			listOfSetsData2[i].contains("hi");
			long timeAfter = new Date().getTime();
			long difference = timeAfter - timeBefore;

			System.out.println("for the set " + setsName[i]
					+ " the time to find hi in data2 " + (difference));

			resultshi[i] = (int) difference;
			
			
		}
		System.out.println("\nfinding '-13170890158' in data2 set:\n");
		for (int i = 0; i < 5; i++) {
			long timeBefore = new Date().getTime();
			listOfSetsData2[i].contains("-13170890158");
			long timeAfter = new Date().getTime();
			long difference = timeAfter - timeBefore;

			System.out.println("for the set " + setsName[i]
					+ " the time to find '-13170890158' in data2 "
					+ (difference));
			
			resultsnumber[i] = (int) difference;

		}
		bestScore = resultsnumber[0];
		bestSet = setsName[0];
		for (int i = 0; i < 5; i++) {
			if (resultsnumber[i] < bestScore){
				bestScore = resultsnumber[i];
				bestSet  = setsName[i];
			}
		}
		
		System.out.println("\n" + bestSet + " is the fastest for finding '-13170890158' in data 2\n");

        System.out.println("comparing finding hi and '-13170890158' in data2 set:\n");
		for (int i = 0; i < 5; i++) {
			System.out.println("for the set : " + setsName[i]
					+ " initialized by data 2. it took " + resultshi[i] + " ml to find hi and "
					+ resultsnumber[i] + " ml to find '-13170890158'");

			}

		}
	}
