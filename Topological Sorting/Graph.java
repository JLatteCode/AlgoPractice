package lab8;

import java.util.ArrayList;
import java.util.Collections;

public class Graph {

    /** Number of vertices in the graph */
    private int numVertices = 0;
    
    /** Adjacency matrix representation of the edges */
    private int[][] adjMatrix;
    
    /** Flag to indicate if this is a directed graph */
    private boolean directed = false;
    
    /** Array of flags for the vertices - used by DFS and BFS algorithms */
    private boolean[] visited;

    /** Stores DFS sequence. */
    private ArrayList<Integer> dfsSeq = new ArrayList<Integer>();
    
    /** Stores TopoSort sequence. */
    private ArrayList<Integer> topoSort = new ArrayList<Integer>();
    
    /** Stores label for vertexs. */
    private ArrayList<String> label = new ArrayList<String>();
    
   

	/**
     * Create a new Graph object.
     * @param N - number of vertices in the graph.
     * @param lab - Label Array for vertices.
     */
    public Graph(int N, ArrayList<String> lab) {
    	
    	/** initazlie label for vertices. */
    	label = lab;
    	
        numVertices = N;
        // Initially the graph has no edges ("empty" adjacency matrix)
        adjMatrix = new int[N][N];
        for (int u = 0; u < N; u++) {
            for (int v = 0; v < N; v++) {
                adjMatrix[u][v] = 0;
            }
        }
        // Mark all vertices as un-visited (although the algorithms using it
        // should also enforce it themselves as needed)
        visited = new boolean[N];
        setAllVisitedFlags(false);
        return;
    }

    /**
     * Set the state of whether this graph is directed or undirected.
     * @param val
     */
    public void setDirected(boolean val) {
        directed = val;
        return;
    }

    /** 
     * Return true if this is a directed graph.
     * @return 
     */
    public boolean isDirected() {
        return directed;
    }

    /**
     * Sets the "visited" flags of all the vertices to the specified value.
     * @param flag
     */
    private void setAllVisitedFlags(boolean flag) {
        for (int v = 0; v < numVertices; v++) {
            visited[v] = flag;
        }
        return;
    }

    /**
     * Add one new edge to the graph, an edge from vertex u to v.
     * @param u
     * @param v
     */
    public void addEdge(int u, int v) {
        if (!directed) {
            // If the graph is undirected, we need two entries in the matrix
            adjMatrix[u][v] = 1;
            adjMatrix[v][u] = 1;
        } else {
            // For directed graphs, we only need one entry in the matrix
            adjMatrix[u][v] = 1;
        }
        return;
    }

    /**
     * Return the degree of a vertex if the graph is undirected.
     * For directed graphs this method is irrelevant and returns -1.
     * @param v
     * @return Degree of the given vertex if the graph is undirected
     * @return -1 if the graph is directed
     */
    public int degree(int v) {
        if (directed) {
            return -1;
        } else {
            int count = 0;
            for (int i = 0; i < numVertices; i++) {
                if (adjMatrix[v][i] > 0) {
                    count++;
                }
            }
            return count;
        }
    }

    /**
     * 
     * @param v
     * @return
     */
    public int inDegree(int v) {
        if (!directed) {
            return -1;
        } else {
            int count = 0;
            for (int i = 0; i < numVertices; i++) {
                if (adjMatrix[i][v] > 0) {
                    count++;
                }
            }
            return count;
        }
    }

    /**
     * 
     * @param v
     * @return
     */
    public int outDegree(int v) {
        if (!directed) {
            return -1;
        } else {
            int count = 0;
            for (int i = 0; i < numVertices; i++) {
                if (adjMatrix[v][i] > 0) {
                    count++;
                }
            }
            return count;
        }
    }

    /**
     * 
     */
    public String toString() {
    	
    	 StringBuilder myStr = new StringBuilder();
    	 
    	 // To create a top label to represent verticies' 
    	 // position for an adj Matrix.
    	 for (int i = 0; i < numVertices; i++) {
    		 	 // i == 0 becasuse we want to add 
    		 	 // a three-chars space only once for the top label.
    			if (i == 0) {
    				myStr.append("   ");
    				// put position values into a top label.
    			 for(int j = 0; j < numVertices; j++) {
    				 
    				 myStr.append(j);
    				 myStr.append(" ");
    				 // j == numOfVertices - 1 to add a new line only once
    				 // when all the positon values are inserted into a top label.
    				 if( j == numVertices-1) {
    					 myStr.append("\n");
    					 myStr.append("   ");
    					 //to draw a borderline for the position values for the top label.
    					 for(int r = 0; r < numVertices; r++) {
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
    	   for (int k : adjMatrix[i]) {
    		   if(k == 1) {
    			   myStr.append(1 + " ");
    		   } else {
    			   myStr.append(0 + " ");
    		   }
    		  
    	   }
    	   // adding a new line to seprate rows for the set of verticies value.
    	   myStr.append("\n");
    	 }
    	 // this will prints out the value of adj matrixs.
    	 return myStr.toString();
        }
    
    /**
     * Perform a Depth-First Search on the graph.
     */
    public void DFS_driver() {
        setAllVisitedFlags(false);
        for (int v = 0; v < numVertices; v++) {
            if (!visited[v]) {
                dfs(v);
            }
            
        }
        /** Reversing DFS sequence and put it into topoSort Array. */
        for (int i = dfsSeq.size() - 1; i >= 0; i--) {
        			topoSort.add(dfsSeq.get(i));
        }
        
        return;
    }

    /**
     * 
     * @param v
     */
    private void dfs(int v) {
    	// System.out.println("Visiting vertex " + v);
        visited[v] = true;
        
        /** Adds visited node to a sequence. */
        dfsSeq.add(v);
        
        for (int w = 0; w < numVertices; w++) {
            if (adjMatrix[v][w] > 0) {
                if (!visited[w]) {
                    dfs(w);
                }
            }
        }
        return;
    }

    /**
     * 
     */
    public void BFS_driver() {
        setAllVisitedFlags(false);
        for (int v = 0; v < numVertices; v++) {
            if (!visited[v]) {
                bfs(v);
            }
        }
        return;
    }
    
    /**
     * 
     * @param start
     */
    private void bfs(int start) {
        System.out.println("BFS visiting vertex " + start);
        visited[start] = true;
        ArrayList<Integer> Q = new ArrayList<Integer>();
        Q.add(start);
        while (Q.size() > 0) {
            int headofQ = Q.get(0);
            for (int w = 0; w < numVertices; w++) {
                if (adjMatrix[headofQ][w] > 0) {
                    if (!visited[w]) {
                        System.out.println("BFS visiting vertex " + w);
                        visited[w] = true;
                        Q.add(w);
                        System.out.println("Q:" + Q);
                    }
                }
            }
            Q.remove(0);
            System.out.println("Q:" + Q);
        }
    }
    
    /** Return array that contains a sequence of DFS. */
    public ArrayList<Integer> getDfsSeq() {
    	return dfsSeq;
    }

    /** Prints DFS sequence with label*/
    public void printDfsSequence() {
    	
    	System.out.println("DFS Sequence: ");
    	
    	  for(int i : dfsSeq) {
                System.out.print(label.get(i) + " ");
            }
    	  System.out.println();
    }
    
    /**  Prints TopoSort Sequence with Label.*/
 public void printTopoSortSequence() {
    	
    	System.out.println("TopoSort Sequence: ");
    	
    	  for(int i : topoSort) {
                System.out.print(label.get(i) + " ");
            }
    	  
    	  System.out.println();
    	
    }
 
 /** Return TopoSort sequence Array. */
 public ArrayList<Integer> getTopoSort() {
		return topoSort;
	}

}
