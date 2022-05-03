

package divisible37;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

// This class reads a text file and find every combination of 3 numbers
// that are divisible by 37 when they are added together.



public class divisible37 {

	public static void main(String[] args) throws FileNotFoundException {
		
		Scanner namepromptscanner = new Scanner(System.in);
		
		System.out.println("Enter the name of the .txt file: ");
		
		
		
	   // prompts user to enter a name of text file.
		File myfile = new File(namepromptscanner.nextLine() + ".txt");
		
		Scanner filereadscanner = new Scanner(myfile);
		
		ArrayList<Integer> numberlist = new ArrayList<Integer>();
		
		
		//Store numbers into ArrayList from a text file. 
		while(filereadscanner.hasNextLine()) {
						
			String data = filereadscanner.nextLine();
			Integer num = Integer.parseInt(data);
			
			numberlist.add(num);
			
		}
		
		// Nested Loop to find every combination of three numbers
		// that are divisible by 37 when added together.
		for(int i = 0; i < numberlist.size()-2; i++) {
			
			
			
			
			for(int j = i+1; j < numberlist.size()-1; j++) {
				
			
				for(int k = j+1; k < numberlist.size(); k++) {
					
					
				if((numberlist.get(i) + numberlist.get(j) + numberlist.get(k))  % 37 == 0) {
					System.out.println("Numbers that are divisible by 37 "
							+ "when they are added together: ");
					
					System.out.println(numberlist.get(i));
					System.out.println(numberlist.get(j));
					System.out.println(numberlist.get(k));
					
				}
			
					
				}
				
			}
			
		}
		

	}

}
