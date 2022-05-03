import java.util.HashMap;

/** Calculate SW recursively. */
public class SW_recursive {
	
	/** Times are needed to calculate how much time method took. */
	static long startTime;
	static long elsapedTime;
	
	/** To store the time took for given m,n value, this HashMap is not used
	 * for dynamic programming. */
	 static HashMap<Integer, Long> SWTime = new HashMap<Integer, Long>();
	 
	 /** needed to capture values for bunch of variables
	  *  when the method is ran for the first time. */
	 static int runCount = 0;
	 
	 /** To store original values of M and N that were to the parameter. */
	 static int originalM;
	 static int originalN;
	 
	 /** Calculate SW recursively and prints out the result 
	  * using a helper */
	public static void SW_recur(int m, int n) {
		
		runCount = 0;
		startTime = System.currentTimeMillis();
		
		/** Print information for the time took, number of ways for path.*/
		System.out.println("SW_Recursive: " + m + " " +  n);
		System.out.println(" N of ways: " + SW_recur_helper(m,n));
		
		System.out.println("Time is: " + SWTime.get(m));
		System.out.println("------------------");

	}
	 
	public static long SW_recur_helper(int m, int n) {
		
		/** capturing values for bunch of variables
		  *  when the method is ran for the first time. */
		if(runCount == 0) {
			
			originalM = m;
			originalN = n;
		
			elsapedTime = 0;
			runCount ++;
			
		}
		
		
		/** manually set value of number of ways as 1 when m and n are 1.
		 * this is base case. */
		if(m == 0 || n == 0) {
			
			return 1;
			
		} else {
			
			/** To calculate elaspedTime when the recursive call is the last one. */
			if(m == originalM - 1 && n == originalN - 1 ) {
			
				
				elsapedTime += System.currentTimeMillis() - startTime;
				
				SWTime.put(originalM, elsapedTime);
		
			}
			
	
			/** Invoke recursive calls. */
			return SW_recur_helper(m - 1, n) + SW_recur_helper(m, n - 1);
			
			
			
		}
		
	}
			
	public static void main(String[] args) {
		
		/**  Runs SW_recursive method multiple times with m,n value changing.
		 * it might reach to a point where overflow occurs */
	for(int i = 0; i < 17; i++) {
	
			SW_recur(i,i);
			
	}
		
	}

}
 