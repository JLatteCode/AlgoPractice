/** Calculate SW dynimacally which is a lot faster than recursive version. */
public class SW_dynamic {
	
	/** Times are needed to calculate how much time method took. */
	static long startTime;
	static long elsapedTime;
	
	/** Array for dynamic programming purpose, it stores value of previous step and returns a value
	 * when next iterative calls need the value. Makes algorithmn a lot quicker. */
	 static int storedWays[][];
	 
	 /** Calculate SW dynimacally. */
	public static void SW_dyna(int m, int n) {
		
		
		
		startTime = System.currentTimeMillis();
		/** Allocate size for the array. */ 
		storedWays = new int [m][n];
		
		 
		 /** initalize weight(distance) values for each path as one. */ 
		for (int i = 0; i < m; i++) {
			
			storedWays[i][0] = 1;
		
		}
		
		for (int j = 0; j < n; j++) {
			
			storedWays[0][j] = 1;
			
		}
		
		/** Iteratively add the numbers of path of previous step to the next one. */ 
		for (int k = 0; k < m  ; k++) {
			
			for(int p = 0; p < n  ; p++) {
				/** if constraint, because we are manually setting up the value for 
				 * storedWays[0][0] as 1 for our base case. */
			if(k > 0 && p > 0 ) {
				
				storedWays[k][p] = storedWays[k-1][p] + storedWays[k][p-1];
			
				}
				
			}
		}
		
		/** 
		 * if constraint, because we set the value of storeWays[0][0] manually and 
		 * it is a base case.
		 * We want to deal with non-base case mainly in the loop. */
		if(m > 0 && n > 0) {
			
			/** Print information for the time took, number of path for our
			 * SW_dynamic algorithmn. */
		elsapedTime += System.currentTimeMillis() - startTime;
		System.out.println("SW_dynamic: " + (m - 1)  + " " + (n - 1));
		System.out.println("Elasped Time: " + elsapedTime);
		System.out.println("Number of ways: " + storedWays[m-1][n-1]);
		
		}
	System.out.println("------------------");
		
	
	}

	
	public static void main(String[] args) {
		/**  Runs SW_dynamic method multiple times with m,n value changing.
		 * it might reach to a point where overflow occurs  */
	for(int i = 0; i < 20; i++) {
		
		SW_dyna(i,i);
		
	}
		
	}

}
 