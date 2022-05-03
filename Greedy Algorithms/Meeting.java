import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import java.util.stream.Stream;

/** Schedule meetings using greedy Algorithmn. */
public class Meeting {
	
	private static int meetingIndex;
	
	/** Stores a name of a meeting. */
	private static String meetingName;
	/** In a text file name of the meeting is stored on the first line relatively. */
	private static final int MEETING_NAME_RELATIVE_POSITION_IN_FILE = 0;
	/** In a 2D 'meetings' array, name is stored like this [index0][0]...[index99][0] */ 
	private static final int MEETING_NAME_INDEX = 0;
	
	
	private static String meetingTime;
	/** In a text file name of the meeting is stored on the second line relatively. */
	private static final int MEETING_TIME_RELATIVE_POSITION_IN_FILE = 1;
	/** In a 2D 'meetings' array, time is stored like this [index0][1]...[index99][1] */ 
	private static final int MEETING_TIME_INDEX = 1;
	
	/** if this value is 0 then we are handling name or if it's 1, then time */
	private static int nameOrTime;

	/** Stores meetings in array. */
	public static String[][] meetings;
	/** Stores Booked Meetings in array. */
	public  static String[][] bookedMeetingsTimes;
	
	/** Reads data for reading requests and stores them into an array. */
	public static void FetchMeeting() throws IOException {
		
		
		System.out.println("Type the file name you want to read: ");
		Scanner mScanner = new Scanner(System.in);
		String fileName = mScanner.nextLine();
		
		
		
		/** Finds how many lines text file has to determine the size of an array. */
		BufferedReader reader = new BufferedReader(new FileReader(fileName + ".txt"));
		int lines = 0;
		while (reader.readLine() != null) lines++;
		reader.close();
		
	    try {
	    	
	      File mFile = new File(fileName + ".txt");
	      Scanner mSecondScanner = new Scanner(mFile);
	      
	      /** (Lines/2) because Meeting and Meeting time is consist of two lines. */
	      meetings = new String[lines/2][lines/2];
	      bookedMeetingsTimes = new String[lines/2][lines/2];
	      
	      /** Read text file line by line. */
	      while (mSecondScanner.hasNextLine()) {
	    	  
	    	  /** Read a line for name and stores it as a local value. */
	    	if(nameOrTime == MEETING_NAME_RELATIVE_POSITION_IN_FILE ) {
	    		
	    		 String nameInFile = mSecondScanner.nextLine();
	    		meetingName = nameInFile;
  	
	    		/** setting the relative position in file to 1 to indicate time after. */
	    		nameOrTime = 1;
	    	}
	    	  	  
	    	 /** Read a line for time and stores it as a local value. */
	    	if(nameOrTime == MEETING_TIME_RELATIVE_POSITION_IN_FILE) {
	    		
	    		String data = mSecondScanner.nextLine();
	    		meetingTime = data;
	    		
	    		/** setting the relative position in file to 0 to indicate name after. */
	    		nameOrTime = 0;
	    	}
	    	
	    
	    	/** Storing value from files into Array. */
	     meetings[meetingIndex][MEETING_NAME_INDEX] = meetingName;
	     meetings[meetingIndex][MEETING_TIME_INDEX] = meetingTime;
	       
	     meetingIndex++;
	     
	      }
	      
	      mSecondScanner.close();
	      
	    } catch (FileNotFoundException e) {
	    	
	      System.out.println("File not found.");
	 
	      
	    }
	  }

	/** Sort Meeting by earliset start Time using Bubble Sort. */
	public static void sortByStart() {
			
		 int n = meetings.length;  
	                        
	        for(int i = 0;i < n; i++){
	            for(int j=0;j < n - 1; j++){
	            	
	            	/** Meeting one is located in array right before Meeting Two.
	            	 * 'One', 'Two' just indicate relative position to each other. */
	            	String meetingOne = meetings[j][1].substring(0,meetings[j][1].indexOf(" "));
	         
	            	String meetingTwo = meetings[j+1][1].substring(0,meetings[j+1][1].indexOf(" "));
	            
	            	/** Change the position of meeting One and Wwo because meetingTwo starts 
	            	 * earlier that meetingOne */
	                if(Integer.parseInt(meetingOne) > Integer.parseInt(meetingTwo))
	                {
	                	/** Temporary varialbes to hold the value of meetingOne. */
	                	String tempMeetingOne = meetings[j][0];
	                    String tempMeetingTwo = meetings[j][1];
	                    meetings[j][0] = meetings[j+1][0];
	                    meetings[j][1] = meetings[j+1][1];
	                    
	                    meetings[j+1][0] = tempMeetingOne;
	                    meetings[j+1][1] = tempMeetingTwo;
	               }
	            }
	        }
	        
	                 }  
	
	/** Sort Meeting by earliset End Time using Bubble Sort. */
	public static void sortByEnd() {
		
		/** Almost simillar logic as sortByStart.
		 * This time we compare the value of meeting end times. */
		
		 int n = meetings.length;  
	                        
	        for(int i = 0;i < n; i++){
	            for(int j=0;j < n - 1; j++){
	            	
	            	String meetingOne = meetings[j][1].substring(meetings[j][1].indexOf(" ") + 1 , meetings[j][1].length());
	        
	            	String meetingTwo = meetings[j+1][1].substring(meetings[j+1][1].indexOf(" ") + 1, meetings[j+1][1].length());
	            
	                if(Integer.parseInt(meetingOne) > Integer.parseInt(meetingTwo))
	                {
	                	String tempMeetingOne = meetings[j][0];
	                    String tempMeetingTwo = meetings[j][1];
	                    meetings[j][0] = meetings[j+1][0];
	                    meetings[j][1] = meetings[j+1][1];
	                    
	                    meetings[j+1][0] = tempMeetingOne;
	                    meetings[j+1][1] = tempMeetingTwo;
	               }
	            }
	        }
	        
	                 }  
	
	/** Sort Meeting by shortest Meeting duration using Bubble Sort. */
	public static void sortByMeetingDuration() {
		
		/** Almost simillar logic as sortByStart.
		 * This time we compare the value of meeting duration. */
		
		int n = meetings.length;  
        
        for(int i = 0;i < n; i++){
            for(int j=0;j < n - 1; j++){
        
            	String meetingOneStartTime = meetings[j][1].substring(0,meetings[j][1].indexOf(" "));
            	String meetingOneEndTime = meetings[j][1].
				substring(meetings[j][1]
						.indexOf(" ") + 1, meetings[j][1].length());
				
            	int meetingOneDuration = Integer.parseInt(meetingOneEndTime) 
            			- Integer.parseInt(meetingOneStartTime);
            	
            	String meetingTwoStartTime = meetings[j+1][1].substring(0,meetings[j+1][1].indexOf(" "));
            	String meetingTwoEndTime = meetings[j+1][1].
				substring(meetings[j+1][1]
						.indexOf(" ") + 1, meetings[j+1][1].length());
            	
            	int meetingTwoDuration = Integer.parseInt(meetingTwoEndTime) 
            			- Integer.parseInt(meetingTwoStartTime);
        
            	String meetingTwo = meetings[j+1][1].substring(0,meetings[j+1][1].indexOf(" "));
            
            
            	
                if(meetingOneDuration > meetingTwoDuration)
                {
                	
                	String tempMeetingOne = meetings[j][0];
                    String tempMeetingTwo = meetings[j][1];
                    meetings[j][0] = meetings[j+1][0];
                    meetings[j][1] = meetings[j+1][1];
                    
                    meetings[j+1][0] = tempMeetingOne;
                    meetings[j+1][1] = tempMeetingTwo;
               }
            }
        }
		
		
	}
			

			/** Checks 'bookedMeetingsTimes' array and see if there is any overlap
			 * for requested meeting time.
			 * @param startTime requested startTime of a meeting.
			 * @param endTime requested endTime of a meeting.
			 */
			public static boolean isTimeBooked(String startTime, String endTime) {
				
				for(int i = 0; i < bookedMeetingsTimes.length; i++) {
					
					
					/** only reads array until there is a data. */
					if(!(bookedMeetingsTimes[i][0] == null)) {
						
	
				int mStartTime = Integer.parseInt(bookedMeetingsTimes[i][0]);
				int mEndTime = Integer.parseInt(bookedMeetingsTimes[i][1]);
				boolean booked;
					
					/** check if there is any overlap for requested meeting time. */
				if(!(mEndTime <= Integer.parseInt(startTime) || Integer.parseInt(endTime) <= mStartTime)) {
					booked = true;
					
					return booked;
				}
				}
				}
				/** Return false if there is no overlap. */
				return false;
			}
		
			/** Ask a user how to scheme a schedule for meetings and make it. */
			public static void schedulMeeting() {
				
			
				System.out.println("How would you like to schedule a meeting? ");
				System.out.println("1 - by earliest meeting start time 2 - by shortest meeting duration time "
						+ "3 - by earlist end time");
				Scanner mScanner = new Scanner(System.in);
				String userSelection = mScanner.nextLine();
				
				switch(userSelection) {
					
				case "1":
					sortByStart();
					break;	
				case "2":
					sortByMeetingDuration();
					break;
				case "3":
					sortByEnd();
					break;
					
				}

				
				String firstMeetingEndTime = meetings[0][1].
						substring(meetings[0][1].indexOf(" ") + 1, meetings[0][1].length());
				String firstMeetingStartTime = meetings[0][1].
						substring(0, meetings[0][1].indexOf(" "));
				
			    /** if meeting is booked store the information into an array. */
				bookedMeetingsTimes[0][0] = firstMeetingStartTime;
				bookedMeetingsTimes[0][1] = firstMeetingEndTime;
				
				/** manually set count as 1 for the 1st meeting 
				 * because it will be out of loop. */
				int count = 1;
				
				
				System.out.println("\n Booked meetings: ");
				/** Prints out the first booked meeting. */
				System.out.println(meetings[0][0]);
				
				for(int i = 1; i < meetings.length; i++) {
		
					
					String secondMeetingStartTime = meetings[i][1].
							substring(0,meetings[i][1].indexOf(" "));
					
					String secondMeetingEndTime = meetings[i][1].
							substring(meetings[i][1].indexOf(" ") + 1,meetings[i][1].length());
					
				/** check if the there is a overlap for the requested meeting time. */
					if((isTimeBooked(secondMeetingStartTime,secondMeetingEndTime)) == false) {
						
					
						bookedMeetingsTimes[count][0] = secondMeetingStartTime;
						bookedMeetingsTimes[count][1] = secondMeetingEndTime;
						
						/** Prints out the rest of the booked meetings. */
					System.out.println(meetings[i][0]);
						
						/** counts how many meetings are booked. */
						count++;
						
						
						
					}
					
				}
			
				System.out.println("Number of Booked meetings: " + count);
				
			}
	
	
	/** Prints All the meeting list(Booked & UnBooked. */
	public static void printArray() {
		
		System.out.println("\n Meetings in an array: ");
		
		for(int i =0; i < meetings.length; i++) {
			
			System.out.println(meetings[i][0] + ": " + meetings[i][1]);
		
			
		}	 
	                         }  
	                          	              
	public static void main(String[] args) throws IOException {
		
		FetchMeeting();
		schedulMeeting();
		
		/** Can be uncommented to display sorted meeting list. */
		//printArray();
		
	
		

		
}
		
}
	
	
	
	
	
	
	
