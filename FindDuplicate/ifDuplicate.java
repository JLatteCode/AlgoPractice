package lab5;

import java.util.Arrays;
import java.util.Random;
import java.lang.Object;

public class ifDuplicate {

	 // Element value of an array will be from 0-999.
	public final static int UPPERBOUND = 1000;
	public final static int ARRAYSIZE = 3000;
	// Store number of times duplicate method ran.
	public final static int NOFTRIES = 50;
	// array to store random numbers. 
	public static int[] array = new int[ARRAYSIZE];
	
	//numbers of times for duplication after this method ran N times. 
	public static int nOfDuplicateBFC;
	public static int nOfDuplicateTC;
	
	//Compute time for Algorithm.
	public static long timeTookDuplicateBFC;
	public static long timeTookDuplicateTC;
	
	//Random object to create random values for an Array.
	static Random rnd = new Random();	
	
	
	
	     //brute force Algorithm to find duplicate values in an Array.
	public static Boolean bfcDuplicate(int[] array) {
		
		long startTimebfc = System.nanoTime();
		
	     
		//nested loop to compare each elements to find duplicates. 
		for(int i = 0; i < array.length; i++) {
			
			for(int j = i+1; j < array.length; j++) {
			// if condition true, duplicate is found, and return true.
				if(array[i] == array[j]) {
				// Increase this variable by one each time there is duplicate.
					nOfDuplicateBFC++;
					
					

					long endTimebfc = System.nanoTime();
					
					timeTookDuplicateBFC += endTimebfc - startTimebfc;
					
					return true;			
			}	
	}
		} return false;
	}
	
	/* run BFC and TC algorithm n of times and prints all specific info:
	 what they are dealing with such as Range of value & Length of Array,
	 n of times Both Algorithm ran, How long they took for computation,
	 probability of duplicate occurrence, etc.. */
	public static void runner(int noftries) {
		
		
		System.out.println("Length of Array: " + array.length);
		System.out.println("Range of Value: 0-" + UPPERBOUND);
		System.out.println("N of Tries: " + noftries);
		
		
		// run each algorithm N of times. 
		for(int j = 0; j < noftries; j++) {
			
			for(int i = 0; i < ARRAYSIZE; i++) {
				// assign random values to an array element.
				array[i] = rnd.nextInt(UPPERBOUND);
				
				// call Both algorithms only when all the values for element is assigned
				// , given ARRAYSIZE.
				if(i+1 == ARRAYSIZE) {
				
				
					// call Algorithm
					bfcDuplicate(array);

					tcDuplicate(array);
					
					// measure time for each Algorithm.
					if(j+1 == noftries) {
						System.out.println("TotalTimetookforBFC: " + timeTookDuplicateBFC);
						System.out.println("AverageTimetookforBFC: " + timeTookDuplicateBFC/noftries);
					System.out.println("TimetookforTC: " +timeTookDuplicateTC);
					System.out.println("AverageTimetookforTC: " + timeTookDuplicateTC/noftries);
					}
				}
			} 
		
	}  
		/* bfcDuplicate(array) and tcDuplicate(array) should return 
		   Same number of Duplicate occurrence. Because they are essentially 
		   doing the same thing. */
		if(nOfDuplicateBFC == nOfDuplicateTC) {
		System.out.println("Numbers of duplicate from both Duplicate method: "
		+ nOfDuplicateBFC + " Each");
		
		System.out.println("Probability of array to contain duplicate: "
		+ ((double)nOfDuplicateBFC / noftries * 100) + "%");
		
		}
		

	}
	
	
	
	
	public static Boolean tcDuplicate(int [] array) {
		
		
		
		// sorting array before using Transform and conquer algorithm. 
		Arrays.sort(array);
		
		//start timing
		long startTimetc = System.nanoTime();
		
		for(int i = 0; i < array.length; i++) {
			
			/* Last element doesn't have any left element to compare value with.
			 * "i < array.length -1" statement stops comparison when we reach the end of
			 * the element.
			 */
			if(i < array.length -1) {
				//check one element and next index element in order.
			if(array[i] == array[i+1]) {
				nOfDuplicateTC++;
				
				// end timing
				long endTimetc = System.nanoTime();
				
				
				// total time took for TC algorithm
				timeTookDuplicateTC += endTimetc - startTimetc;
				
				// if duplicate found return true.
				return true;
			
			}
			}
			
		}
		
		return false;
	}
	
	
public static void main(String[] args) {
		
	runner(NOFTRIES);

	}
	
}
