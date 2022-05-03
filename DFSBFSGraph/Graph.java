package lab7;

import java.util.ArrayList;

// An adjacency Matrix of a graph. Includes Algorithms such as DSF and BSF.
public class Graph {


private boolean adjMatrix[][];
// Number of Verticies for adjacency Matrix.
private int numOfVertices;
// Set the value true if the graph is directed.
private boolean directed;
// Track nodes that are visited.
private boolean visit[];

// Holds stack values for DFS Algorithm.
private ArrayList<Integer> stack = new ArrayList<Integer>();
// Holds stack values for BFS Algorithm.
private ArrayList<Integer> queue = new ArrayList<Integer>();

// Initalize the size of adjMaxtrix.
public Graph(int numVertices) {
 this.numOfVertices = numVertices;
 //Initalize the size of adjMatrix by the numbers of given verticies.
 adjMatrix = new boolean[numVertices][numVertices];
 //initalize the size of visit[] array by the numbers of given verticies.
 visit = new boolean[numOfVertices];
 
}

// Creating edges between two verticies for an undirected graph.
public void addEdge(int u, int v) {
 adjMatrix[u][v] = true;
 // Set the value of [v][u] true as well 
 // because this method is designated for an undirected graph.
 adjMatrix[v][u] = true;
}

// Creating edges between two verticie for a directed graph.
public void addDirectedEdge(int u, int v) {
	 adjMatrix[u][v] = true;

	}

// Prints out adjacency Matrix
public String toString() {
	
 StringBuilder myStr = new StringBuilder();
 
 // To create a top label to represent verticies' 
 // position for an adj Matrix.
 for (int i = 0; i < numOfVertices; i++) {
	 	 // i == 0 becasuse we want to add 
	 	 // a three-chars space only once for the top label.
		if (i == 0) {
			myStr.append("   ");
			// put position values into a top label.
		 for(int j = 0; j < numOfVertices; j++) {
			 
			 myStr.append(j);
			 myStr.append(" ");
			 // j == numOfVertices - 1 to add a new line only once
			 // when all the positon values are inserted into a top label.
			 if( j == numOfVertices-1) {
				 myStr.append("\n");
				 myStr.append("   ");
				 //to draw a borderline for the position values for the top label.
				 for(int r = 0; r < numOfVertices; r++) {
					 myStr.append("— ");				 
				 }					 
			 }		  
		}
		 // adding new line to separate top label and set of the values for verticies.
		 myStr.append("\n");
	 }
	 
		// adding | top separate side label from the 
		// set of the values of the verticies.
	 myStr.append(i + "| ");
	 // check if the value of adjMatrix[i] is true or false.
	 // and add it to the string accordingly.
   for (boolean k : adjMatrix[i]) {
     myStr.append((k ? 1 : 0) + " ");
   }
   // adding a new line to seprate rows for the set of verticies value.
   myStr.append("\n");
 }
 // this will prints out the value of adj matrixs.
 return myStr.toString();
}

// For an undirected Graph.
// return the value of degree of a verticie.
public int degree(Graph g, int v) {
		
	// initalize the value.
	int numOfDegree = 0;
	
	// loops through one row for verticies values at a time.
	for(int i = 0; i < g.numOfVertices; i++) {
	// if a node from a column is true then add 1 to numOfDegree variable.
	if(g.adjMatrix[v][i] == true) {
		numOfDegree ++;
		
	}
	
}
	return numOfDegree;
}

// check if the graph is Directed.
public boolean isDirected(Graph g) {
	
   // set a row position to loop through.
	for(int i = 0; i < g.numOfVertices; i++) {
		// set a column position to loop through.
		for(int j = 1; j < g.numOfVertices; j++) {
		
			// The value of adjMatrix[i][j] must be true before
			// doing a comparison from a reverse position.
			// Because we are only using verticies that are connected
			// with edges.
			if(g.adjMatrix[i][j] == true) {
			
				//check if reverse position's value is the same.
			if(g.adjMatrix[i][j] == g.adjMatrix[j][i]) {
				
				System.out.println("Graph is not directed"  );
				directed = false;
				// if it isn't return false value.
				return directed;
			}
			}
			
		}
		}

	directed = true;
	return directed;
}

// For a directed Graph.
// return the value of inDegree for a vertice.
public int inDegree(Graph g, int v) {
	
	// initalize the value;
	int numOfDegree = 0;
	
	// check only if the graph is directed if not return -1.
	if(g.isDirected(g) == true) {
		
		// loops through multiple row values of a column.
	for(int i = 0; i < g.numOfVertices; i++) {
	// if indegree is found, add 1 to the numOfDegree variable.
	if(g.adjMatrix[v][i] == true) {
		numOfDegree ++;
		
	}
	
	}
	return numOfDegree;
	
	}
	
	return -1;
}
//For a directed Graph.
//return the value of outDegree for a vertice.
public int outDegree(Graph g, int v) {
	
	
int numOfDegree = 0;

if(g.isDirected(g) == true) {	

	for(int i = 0; i < g.numOfVertices; i++) {
		// loops through multiple column values of a row.
	if(g.adjMatrix[i][v] == true) {
		numOfDegree ++;
		
	}
	
	}
	return numOfDegree;
}
return -1;
	
}

// returns a value for the starting point for BFS, DFS algorithmn.
public int startPoint() {
	// Start loops from adjMatrix[0][0] and find the first vertice that has an edge.
for(int i = 0; i < numOfVertices; i++) {
		
		for(int j = 0; j < numOfVertices; j++) {
			// first vertice with an edge is found.
			if(adjMatrix[i][j] == true) {
				
				return i;		
			}
		}

}
return -1;

}

// dfs Algorithmn.
public void dfs() {
	
	// Initalize of the values of visit[] array as false.
	// before begining dfs algorithmn.
	for(int i = 0; i < numOfVertices; i++) {
		
		visit[i] = false;
	}
	
	// invoke startpoint() method to receive a start Node value.
	int startingNode = startPoint();

	//using a dfs algorithmn helper.
	 dfs_helper(startingNode);
	 
	 }
	
	

public void dfs_helper(int startingNode) {
	
	System.out.println("DFS: ");
	System.out.println("StartingNode: " + startingNode);
	
	//adds starting node to a stack
	 stack.add(startingNode);
	System.out.println(startingNode + " visited.");
	// Mark the starting node as visited.
	visit[startingNode] = true;

	// The usage of this will be explained soon in the other comment.
	int trackerOfi;
	
	// loops through column values of a row
for(int i = 0; i < numOfVertices; i++) {
	
	       // The left index value of a adjMatrix will come from the top position
		   // value of a stack. And, as soon as we 
		   //find adjMatrix[topStackValue][i] as true. i will become
		   // the top value of the stack.
		 if(adjMatrix[stack.get(stack.size() - 1)][i] == true && visit[i] == false) {
		
			 // if adjMatrix[topStackValue][i] is true then add i value
			 // to the top of the stack.
			 stack.add(i);
			 
			 System.out.println(i + " visited.");
			 // Mark the i position of vertice as visited.
			 visit[i] = true;
			 // Set the value of i as -1 because when the 
			 // the top value of a stack is changed
			 // we want to loop adjMaxtrix[newTopvalue][i] again 
			 // from adjMatrix[newTopValue][0].
			 // The reason why it isn't i = 0 is because i's value will +1
			 // after the end of the loop by i++;
			 i = -1;
			 
		 }
		

		 else {
			 // Tracker of i exists because the value of i is set as -1
			 // from the 'if condition' above -> if(adjMatrix[stack.get(stack.size() 
			 // - 1)][i] == true && visit[i] == false)
			 // we want to keep the last value of i before chaning the value of it
			 // because we want to capture a moment when 
			 // we reached the end of the column index.
			trackerOfi = i;
			 
			// Treackerofi is used to check if we reached end index
			// value(for column) of a adjMatrix 
			 if(trackerOfi == numOfVertices-1) { 

			 // Check if the top stack value is same as
			 // Starting node value.
			 // To prevent an error by attempting to remove a node
		     // from an empty stack.
			 if(stack.get(stack.size() - 1) != startingNode) {
			 
			 stack.remove(stack.size() - 1);
			 i = -1;
			 
			 }
			
			 }
			 
			
		 
} }
		 
} 
		 
// BFS algorithmn.
public void bfs() {
	
for(int i = 0; i < numOfVertices; i++) {
		
		visit[i] = false;
	}
	
	int startingNode = startPoint();
	
	bfs_helper(startingNode);
}

public void bfs_helper(int startingNode) {
	
	System.out.println("BFS: ");
	System.out.println("StartingNode: " + startingNode);
	
	queue.add(startingNode);
	System.out.println(startingNode + " visited.");
	visit[startingNode] = true;
	// This exist because we have to keep checking
	// the bottom of stack as opposed to DFS Algorithmn.
	int zero = 0;
	
	for(int i = 0; i < numOfVertices; i++) {
		
		// check if column value is true and see if that node was
		// visited.
		if(adjMatrix[queue.get(zero)][i] == true && visit[i] == false) {
		
			// Keep adding i values that are true into the queue.
			queue.add(i);
		    System.out.println(i + " visited");
		    // mark node i as visited after adding all the i values
		    // to the queue so we won't visit them again.
		    visit[i] = true;
    
	} else if(i == numOfVertices - 1) {
    	// remove the botton index value of the queue
		// after we looped through the columns of a row.
		// to check on the next queue.
    	queue.remove(0);
    	
    	i = -1;
    	
    }
	
}
}

public static void main(String args[]) {
	//Creating graph.
 Graph g = new Graph(6);

 // adding edges.
 g.addEdge(0, 1);
 g.addEdge(0, 3);
 g.addEdge(1, 5);
 g.addEdge(2, 5);
 g.addEdge(3, 4);
 g.addEdge(4, 5);
 
 // printing adjacency matrix.
 System.out.print(g.toString());
}
}