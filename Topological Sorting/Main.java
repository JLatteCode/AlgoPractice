package lab8;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {


        // This is the directed-graph example from the Lab7 handout
        // It is harmless to use here for another example in Lab8, but
        // it is not one of the examples given in the Lab8 handout.
        // However, look at the "makeG4()" function to see how this 
        // Graph class requires you to set a flag for a directed graph.
    	/** Graph 1 -----------------------------------------*/ 
    	System.out.println("G1: ");
    	
    	ArrayList<String> labelG1 = new ArrayList<>(Arrays.asList(
    			"a", "b", "c", "d", "e", "f"));
    	
        Graph G = new Graph(6, labelG1);
		
		G.addEdge(0, 1); // a->b
		G.addEdge(0, 5); // a->f
		G.addEdge(0, 4); // a->e
		G.addEdge(1, 2); // b->c
		G.addEdge(3, 1); // d->b
		G.addEdge(3, 2); // d->c
		G.addEdge(4, 3); // e->d
		G.addEdge(5, 2); // f->c
		G.addEdge(5, 4); // f->e
		
		
		 System.out.println(G.toString());
	        G.DFS_driver();
	        G.printDfsSequence();
	        G.printTopoSortSequence();

	        
	        /** Graph 2 -----------------------------------------*/ 
	        System.out.println("\n G2: ");
	        
	        ArrayList<String> labelG2= new ArrayList<>(Arrays.asList(
	    			"w", "x", "y", "z"));
	        
	   Graph G2 = new Graph(4, labelG2);
	   
	   G2.addEdge(0,1); // w -> x
	   G2.addEdge(0,2); // w -> y
	   G2.addEdge(0,3); // w -> z
	   G2.addEdge(1,3); // x -> z
	   G2.addEdge(2,3); // y -> z
	   
	   G2.DFS_driver();
	   G2.printDfsSequence();
       G2.printTopoSortSequence();
	        
	   
	
		   
        
       
	   
	   /** Graph 3 -----------------------------------------*/ 
       System.out.println("\n G3: ");
       
       ArrayList<String> labelG3= new ArrayList<>(Arrays.asList(
   			"Suspender", "Belt", "UnderWear", "Shirt", 
   			"Jacket", "Pants", "tie", "shoes", "socks"));
       
       Graph G3 = new Graph(9, labelG3);
		
     		G3.addEdge(0, 4); // Suspender -> Jacket
     		G3.addEdge(1, 4); // Belt -> Jacket
     		G3.addEdge(2, 5); // UnderWear -> Pants
     		G3.addEdge(3, 4); // Shirt -> Jacket
     		G3.addEdge(3, 6); // Shirt-> Tie
     		G3.addEdge(3, 0); // Shirt-> Suspenders
     		G3.addEdge(5, 0); // Pants-> Suspenders
     		G3.addEdge(5, 1); // Pants-> Belts
     		G3.addEdge(5, 7); // Pants-> Shoes
     		G3.addEdge(6, 4); // Tie -> Jacket
     	
     		 G3.DFS_driver();
     		 G3.printDfsSequence();
     	     G3.printTopoSortSequence();

    
     	   
     	  /** Graph 4 -----------------------------------------*/ 
  
     	  System.out.println("\n G4: ");
          
          ArrayList<String> labelG4= new ArrayList<>(Arrays.asList(
      			"1", "2", "3", "4", 
      			"5", "6"));
     	   
     	  Graph G4 = new Graph(6, labelG4);
  		
   		G4.addEdge(0, 3); // 1 -> 4
   		G4.addEdge(1, 0); // 2 -> 1
   		G4.addEdge(1, 2); // 2 -> 3
   		G4.addEdge(1, 3); // 2 -> 4
   		G4.addEdge(3, 2); // 4 -> 3
   		G4.addEdge(4, 0); // 5 -> 1
   		G4.addEdge(4, 1); // 5 -> 2
   		G4.addEdge(4, 5); // 5 -> 6
   		G4.addEdge(5, 1); // 6 -> 2
   		G4.addEdge(5, 2); // 6 -> 3
   		
   		 G4.DFS_driver();
   		 G4.printDfsSequence();
 	     G4.printTopoSortSequence();
 	     
 	    /** Graph 5 -----------------------------------------*/ 
	 
 	    System.out.println("\n G5: ");
        
        ArrayList<String> labelG5= new ArrayList<>(Arrays.asList(
    			"a", "b", "c", "d", 
    			"e", "f"));
        
 Graph G5 = new Graph(5, labelG5);
  		
   		G5.addEdge(0, 1); // a -> b
   		G5.addEdge(0, 2); // a -> c
   		G5.addEdge(1, 2); // b -> c
   		G5.addEdge(1, 4); // b -> e
   		G5.addEdge(2, 4); // c -> e
   		G5.addEdge(3, 1); // d -> b
   		G5.addEdge(3, 2); // d -> c

   		 G5.DFS_driver();
   		 G5.printDfsSequence();
 	     G5.printTopoSortSequence();
}
}

