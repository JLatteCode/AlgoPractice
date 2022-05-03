package lightPattern;

import java.util.ArrayList;

public class lightPattern {

	public static ArrayList<String> genperm(int n) {
		
		String Array[] = { "0", "1", "2" };

		ArrayList<String> allperm = new ArrayList<String>();

		if (n == 1) {

			for (int i = 0; i < Array.length; i++) {

				allperm.add(Array[i]);

			}
		}
		if (n > 1) {
			ArrayList<String> smallerperm = genperm(n - 1);

			for (String p : smallerperm) {

				for (int i = 0; i < Array.length; i++) {

					String currentperm = p + Array[i];

					allperm.add(currentperm);
				}
			}
		}
		return allperm;
	}
	
	
	
	public static ArrayList<String> gennorepeat(int n) {
		
		String Array[] = { "0", "1", "2" };

		ArrayList<String> allperm = new ArrayList<String>();

		if (n == 1) {

			for (int i = 0; i < Array.length; i++) {

				allperm.add(Array[i]);

			}
		}
		if (n > 1) {
			
			ArrayList<String> smallerperm = gennorepeat(n - 1);

			for (String p : smallerperm) {

				for (int i = 0; i < Array.length; i++) {

		     if((p.substring(p.length() - 1).equals(Array[i])) == false) {
		    
		    	 
		    	 String currentperm = p + Array[i];

					allperm.add(currentperm);
		    	 
		     }
					
				}
			}
		}
		return allperm;
	}
	
	
	

	public static void main(String[] args) {
		
	   System.out.println("When genperm(3): ");
		System.out.println(genperm(3));
		System.out.println("When gennorepeat(3): ");
	   System.out.println(gennorepeat(3));
		
		System.out.println("Generating all patterns of a given length: ");
		
		for(int i = 1; i<=10; i++) {
			
			
			System.out.println("Length " + i + " produces " + genperm(i).size() + " patterns");
		}
			
		System.out.println("Generating patterns without double-digits: ");
		
		for(int j = 1; j <=10; j++) {
			
		System.out.println("Length " + j + " produces " + gennorepeat(j).size() + " patterns" );
			
		}
			
	}

		

	}

