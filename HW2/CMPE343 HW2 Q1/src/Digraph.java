//-----------------------------------------------------
// Title: Flight Network class
// Author: Doðukan Us
// ID: 47113751436
// Section: 03
// Assignment: 2
// Description: This class is to define the structure of Directed Graph object inspired from textbook and slides
//-----------------------------------------------------

public class Digraph
{
	private final int V;
	private int E;
	private Bag<Integer>[] adj; //uses Bag array for adjacency list
	
	
	
	//Constructor which creates an empty digraph
	public Digraph(int V)
		{
			this.V = V;
			this.E = 0;
			adj = (Bag<Integer>[]) new Bag[V];
			for (int v = 0; v < V; v++)
				adj[v] = new Bag<Integer>();
		}
	
	//Returns values of V and E which are private
	public int V() { return V; }
	public int E() { return E; }
	
	//Adds w to v's adjacency list
	public void addEdge(int v, int w)
	{
		adj[v].add(w);
		E++;
	}
	
	public Iterable<Integer> adj(int v)
	{ return adj[v]; }
	
	//Reverses the direction of the edges
	public Digraph reverse()
	{
		Digraph R = new Digraph(V);
		for (int v = 0; v < V; v++)
			for (int w : adj(v))
				R.addEdge(w, v);
		return R;
	}
}