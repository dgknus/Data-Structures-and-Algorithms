//-----------------------------------------------------
// Title: Path Finder class
// Author: Doðukan Us
// ID: 47113751436
// Section: 03
// Assignment: 2
// Description: This class is to make operations to find the paths between treasure and the start vertex
//-----------------------------------------------------

import java.util.ArrayList;

public class PathFinder {
	
	
	ArrayList<Integer> treasures = new ArrayList<Integer>(); //List of treasures in the graph
	ArrayList<int[]> paths = new ArrayList<int[]>(); //List of calculated paths
	
	public PathFinder(ArrayList<Integer> t) {
		//--------------------------------------------------------
		// Summary: Constructor
		// Precondition: t is an integer list
		// Postcondition: the list t is initialized.
		//--------------------------------------------------------	
		
		treasures = t;
	}
	
	
	public ArrayList<int[]> findPaths(Digraph g,int v) {
		//--------------------------------------------------------
		// Summary: Function called from main function
		// Precondition: g is a directed graph and v is an integer
		// Postcondition: calculated paths in paths list are returned
		//--------------------------------------------------------	
		
		boolean [] visited; //boolean array of visited vertices
		ArrayList<Integer> path; // list of vertices which is the part of current path
		
		// loop for calling the function for each treasure, so we calculate path for each treasure seperately
		for(int e: treasures) {
			
			// initializing a boolean with the size of number of vertices in the graph
			visited = new boolean [g.V()];
			//initializing the path list
			path = new ArrayList<Integer>(); 
			
			// we add the first vertex to the list
			path.add(v);
			
			// call the function to calculate all the paths before returning it
			findAllPaths(g, v, e, visited, path);
			
		}
		// returns the calculated paths
		return paths;
	}
	
	private void findAllPaths(Digraph g, int v, int e, boolean[] visited, ArrayList<Integer> path) {		
		//--------------------------------------------------------
		// Summary: Function to calculate path towards the treasure, works recursively
		// Precondition: g is a directed graph, v and e (treasure vertex) are integers and path is a list of integers
		// Postcondition: function is called recursively till finding a path to the treasure, found path is added to the paths list
		//--------------------------------------------------------		
		
		// if the current node v is treasure, it means we reached to the target
		if(v == e) {
			// initiliazing a temporary integer array with the same size of path list
			int[] temp = new int[path.size()];
			
			// inserting the vertices in path list to the temporary array
			for(int i = 0; i < path.size(); i++) {
				temp[i] = path.get(i);
			}
			paths.add(temp); // inserting the temporary array which holds the path through the treasure to the paths list
			return; // returns void since we don't need to search for paths anymore for that treasure
		}
		
		// marks the current vertex visited to avoid getting stuck in a loop
		visited[v] = true;
		
		// loop iterates for each adjacent vertex of current vertex v, we search for treasure vertex
		for(int w : g.adj(v)) {
			// if the adjacent vertex is not visited, we visit it
			if (!visited[w]) {
				
				// add the adjacent vertex to the current path
				path.add(w);
				// we call the function for adjacent vertices
				findAllPaths(g, w, e, visited, path);
				
				/*
				 * If the recursive call hits the bottom and returned to the this function call again
				 * it means that we couldnt find a path through the treasure vertex, so we remove the
				 * this vertex from path and try another vertex
				*/
				path.remove(Integer.valueOf(w)); // used Integer.valueOf(Obj) function to find the integer object to remove, not the index.
			}
		}
		
	}

}
