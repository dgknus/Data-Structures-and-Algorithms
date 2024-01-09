//-----------------------------------------------------
// Title: LeatPoleCalculator Class
// Author: Doðukan Us
// ID: 47113751436
// Section: 03
// Assignment: 3
// Description: This class is to calculate the minimum spanning tree in a given edge weighted graph
//				by using Prim's algorithm inspired from the textbook and slides
//-----------------------------------------------------

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class LeastPoleCalculator {
	
	WeightedGraph g; // Graph object
	private boolean[] marked; // MST vertices
	private EdgeQueue mst; // MST edges
	private PriorityQueue<Edge> pq; // PQ of edges 
	private Comparator<Edge> comparator = new EdgeComparator();
	
	
	public LeastPoleCalculator(WeightedGraph G) {
		//--------------------------------------------------------
		// Summary: Constructor that initializes the variables and calls the function to calculate MST
		// Precondition: G is a WeightedGraph
		// Postcondition: The variables are initialized and primMST() function is called
		//--------------------------------------------------------
		
		this.g = G;
		pq = new PriorityQueue<Edge>(comparator);
		mst = new EdgeQueue();
		marked = new boolean[G.V()];
		
		//function call
		primMST();
	}
	
	public void primMST() {
		//--------------------------------------------------------
		// Summary: Function to calculate MST by using Prim's algorithm
		// Precondition: none
		// Postcondition: The MST is calculated and stored in mst queue
		//--------------------------------------------------------		
		
		// visiting the 0'th vertex first
		visit(g, 0);
		
		
		while (!pq.isEmpty() && mst.length() < g.V() - 1){
			
			Edge e = pq.poll();
			int v = e.either(), w = e.other(v);
			// if both vertices of the edge is marked
			if (marked[v] && marked[w]) continue;
			mst.enQueue(e);
			//visit if vertex is not marked
			if (!marked[v]) visit(g, v);
			if (!marked[w]) visit(g, w); 
			
		}
		
	}
	
	private void visit(WeightedGraph G, int v){
		//--------------------------------------------------------
		// Summary: Visits the given vertex v and mark as true
		// Precondition: G is Weighted Graph and v is integer
		// Postcondition: Vertex v is marked as true and its unmarked adjacent vertices are added to priority queue
		//--------------------------------------------------------		
		
		// mark v as visited
		marked[v] = true;
		
		// for each adjacent vertex of v
		for (Edge e : G.adj(v)) {
			
			// if not marked as visited
			if (!marked[e.other(v)]) {
				// add the edge to the priority q
				pq.add(e);
			}	
		}
		
	}
	
	 public EdgeQueue calculate(){
		//--------------------------------------------------------
		// Summary: Function to return the calculated mst
		// Precondition: none
		// Postcondition: The calculated MST is returned 
		//--------------------------------------------------------		
		
		 // returns the edges of mst
		 return mst; 
	}


}
