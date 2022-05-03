package lab4;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

// This program creates a pass code by doing ->
// 1: Read a data file 
// 2: Sort element in Array by ascending order.
// 3: Find smallest missing value in that sorted array with "zero fillings".
// 4. Print out 2 and 3. 

public class Find_the_Passcode {

	public static void main(String[] args) throws IOException {
		
		//
		// Set the input data file name here
		//
		String fn = "length_2_N_44.txt";

		//
		// Call helper function to read the input file
		//
		Integer[] data = read_array(fn);
		
		//
		// We need to know the length of the "strings" in this data file (for output 
		// purposes later), but we read and saved them as integers, so the best way to 
		// determine this now is to loop over the list, find the largest item, and 
		// assume that all of the items are as long as that one.
		//
		Integer max = 0;
		for (int i=0; i<data.length; i++) {
			if (data[i] > max) {
				max = data[i];
			}
		}
		int length_of_passcodes = String.valueOf(max).length();
		System.out.println("length of passcodes is " + length_of_passcodes);
		
		//
		// Print out the list of input data, for testing only
		//
		for (int i=0; i<data.length; i++) {
			//
			// Format the numbers with the correct number of leading zeroes as
			// needed according to the length of the passcodes being processed.
			//
			String fmt = "%0" + length_of_passcodes + "d";
			System.out.println(String.format(fmt, data[i]));
		}
		System.out.println(data.length + " data items in the file");
		
	
		

sort_array(data);


Scanner sc = new Scanner(System.in);


System.out.println("Enter the passcode size: ");
//prompts user for the digit size for elements for array.
int digitsize = sc.nextInt();

//print out sorted array.
print_Array(data, digitsize);

// print out smallest missing element in a array for a passcode with "0" fillings
System.out.println("Passcode(Smallest missing number) is: ");
System.out.println(String.format("%0" + digitsize 
		+ "d", smallest_missing( data, 0, data.length)));

	}
	
	
	
	// Sorts array using divide and conquer Algorithm 
	// This function chops array by half recursively and
	// pass the Parameter into helper "Merge_Array()" for sorting array.
	public static void sort_array(Integer [] data) {
			
		
		// Left and Right side of chopped Array.
		Integer [] tempArrayL;
		Integer [] tempArrayR;
		
	
		
		if(data.length > 1) {
			
			
			
	    // checking if array is even length.        
		if(data.length % 2 == 0) {    
			
		// Chops a full array by half and copy into teampArray:L&R.            
		tempArrayL = Arrays.copyOfRange(data, 0, data.length/2);
		                              
		tempArrayR = Arrays.copyOfRange(data, data.length/2, data.length);
		// if array is odd length then make it a even length by adding 1.
		// because dividing '11' sized array would be 5.5.
		// We don't want any decimal points.
		} else {                         
			tempArrayL = Arrays.copyOfRange(data, 0, (data.length + 1) / 2 );
			                                        
		                                        
			tempArrayR = Arrays.copyOfRange(data, (data.length + 1) / 2  , data.length);
			
		}
		
		
		// recursive call to divide array into half for left and right side.
		sort_array(tempArrayL);
		sort_array(tempArrayR);
	
		
		merge_array(tempArrayL,tempArrayR, data);
		
		}

		}
		

	
	// Receive a half cut arrays from sort_array() and a original array.
	// help with sorting and merging array elements.
	public static void merge_array(Integer [] arrayL, 
			Integer [] arrayR, Integer [] fullArray) {
		
		
		
		int i = 0;	int j = 0; int k = 0;
		
		while(i < arrayL.length && j < arrayR.length ) {
			// compare the first element of both sides array.
			
			if(arrayL[i] <= arrayR[j]) {
				//left side's element is smaller. So,
				// assign it to the first element of fullArray[]. 
				fullArray[k] = arrayL[i]; 
				
				
				// increase the i value so next element of arrayL compares
				// the value with the arrayR's first element again.	
				i++; 
				
				
			} else {
				// does exactly the same thing as the above one but Right side is bigger.
				fullArray[k] = arrayR[j];  
				
				j++; 
						
			} 
			// increase K so assigning value to fullArray[] goes by like
			/// fullArray[0].. fullArray[1].. fullArray[2].. fullArray[3]...
			k++;
			
			
		}
		
		// when there is no element to be compared for left and right side of array.
		// check if left side of array still has element left.
		while (i < arrayL.length ) {
			// because it did, copy left side of array's left element into fullArray[]
			fullArray[k] = arrayL[i];
			i++;
			k++;
		}
		
		// does exactly what above does but this time right side has element left.
		while (j < arrayR.length ) {
			fullArray[k] = arrayR[j];
			j++;
			k++;
		}
		

		

		
		
		}
		
	// Ask a user for the digit size and print out an array with '0' fillings
	// e.g) if digit size should be three, 3 becomes 003.
	// this is for passcode size.
	public static void print_Array(Integer array[], int digitsize) {
		
		
	
	    System.out.println("Sorted Array: ");
		
		for(int i = 0; i < array.length; i ++) {
			
			// does "0" filling formatting for an array and print out
			System.out.println(String.format("%0" + digitsize + "d", array[i]));
			
		}	
	}
	// finds out the smallest missing element in an array and return the value.
	public static int smallest_missing(Integer [] array, int indexlow, int indexhigh) {
		
		if (indexlow > indexhigh) {
			
			return indexlow;
		}
		
		// finding middle index value for array.
		int indexmid = indexlow + (indexhigh - indexlow) / 2;
		// check the mid value of array.
		if (array[indexmid] == indexmid) {
			// if this matches check the right side of array.
			return smallest_missing(array, indexmid + 1, indexhigh);
			
		} else {
			// does what above does but check the left side of array.
			return smallest_missing(array, indexlow, indexmid - 1);
			
		}
		
		

		
	}
	

	public static Integer[] read_array(String filename) throws IOException {
		//
		// Reads the input file given by "filename", assumed to contain a list of
		// integer numbers. Stores the numbers into an array and returns the
		// array.
		//
		File file = new File(filename);
		Scanner sc = new Scanner(file);

		//
		// Read the items initially into an ArrayList (for dynamic growth)
		//
		ArrayList<Integer> input_list = new ArrayList<Integer>();		
		while (sc.hasNext()) {			
			int n = sc.nextInt();
			input_list.add(n);
		}

		//
		// Copy the ArrayList to an Integer[] array of the proper size
		//
        Integer[] arr = new Integer[input_list.size()]; 
        arr = input_list.toArray(arr); 
		return arr;
	}
}
