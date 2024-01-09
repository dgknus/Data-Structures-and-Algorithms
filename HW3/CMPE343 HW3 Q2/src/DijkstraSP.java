//-----------------------------------------------------
// Title: DijkstraSP Class
// Author: Doðukan Us
// ID: 47113751436
// Section: 03
// Assignment: 2
// Description: This class is to calculate the shortest paths through all vertices from a source city in a given edge weighted graph
//				inspired from the textbook and slides.
//-----------------------------------------------------

import java.util.Stack;

public class DijkstraSP{
 
	private Edge[] edgeTo; // distTo[v] = distance  of shortest s->v path
	private int[] distTo; // edgeTo[v] = last edge on shortest s->v path
	private IndexMinPQ<Integer> pq; // priority queue of vertices
	
	
	// Computes a shortest-paths tree from the source vertex s to every other
    // vertex in the edge-weighted graph
	public DijkstraSP(WeightedGraph G, int s){
	
		edgeTo = new Edge[G.V()];
		distTo = new int[G.V()];
		pq = new IndexMinPQ<Integer>(G.V());
		for (int v = 0; v < G.V(); v++)
		distTo[v] = Integer.MAX_VALUE;
		distTo[s] = 0;
		pq.insert(s, 0);
		// relax vertices in order of distance from s
		while (!pq.isEmpty()){
			
			int v = pq.delMin();
			for (Edge e : G.adj(v)) {
				relax(e, v);
			}
		}
	}
	
	// relax edge e and update pq if changed
	private void relax(Edge e, int current){
		
		int v, w;
		if(e.either() == current){
			
			v = e.either();
			w = e.other(e.either());
		}
		else {
			w = e.either();
			v = e.other(e.either());
		}
		
		if (distTo[w] > distTo[v] + e.weight()){
		
			distTo[w] = distTo[v] + e.weight();
			edgeTo[w] = e;
			if (pq.contains(w)) pq.decreaseKey(w, distTo[w]);
			else pq.insert (w, distTo[w]);
		}
	}
	
	//Returns the length of a shortest path from the source vertex to vertex v.
	public int distTo(int v) {
        return distTo[v];
    }
	
	//Returns a shortest path from the source vertex to vertex v
	public Edge[] pathTo(int v) {

		if (distTo[v] == Integer.MAX_VALUE) {
	        // Vertex v is not reachable from the source vertex;
	        return null;
	    }
	    Stack<Edge> path = new Stack<>();
	    for (Edge e = edgeTo[v]; e != null; e = edgeTo[e.other(v)]) {
	    	if(path.isEmpty()) {
	    		path.push(e);
	    	}
	    	else if(path.elementAt(path.size()-1) != e)
	        	path.push(e);
	        v = e.other(v);
	    }
	    
	    Edge[] edges = new Edge[path.size()];
	    
	    int index = 0;
	    while (!path.isEmpty()) {
	        edges[index++] = path.pop();
	    }
	    
	    return edges;
	}
	
} 