//-----------------------------------------------------
// Title: Weighted Graph class
// Author: Doðukan Us
// ID: 47113751436
// Section: 03
// Assignment: 2
// Description: This class is to define the structure of Weighted Graph object inspired from textbook and slides
//-----------------------------------------------------

public class WeightedGraph {
	
	private final int V;
	private int E;
	private final Bag<Edge>[] adj; //uses Bag array for adjacency list
	
	
	
	//Constructor which creates an empty digraph
	public WeightedGraph(int V)
		{
			this.V = V;
			this.E = 0;
			adj = (Bag<Edge>[]) new Bag[V];
			for (int v = 0; v < V; v++) {
				adj[v] = new Bag<Edge>(); 
			}
		}
	
	//Returns values of V and E which are private
	public int V() { return V; }
	public int E() { return E; }
	
	//Adds edge to its either and other vertices' adjacency list
	public void addEdge(Edge e)
	{
		int v = e.either(), w = e.other(v);
		 adj[v].add(e);
		 adj[w].add(e); 
		E++;
	}
	
	//returns the adjacency list of vertex v given as the parameter
	public Iterable<Edge> adj(int v)
	{ return adj[v]; }

}
