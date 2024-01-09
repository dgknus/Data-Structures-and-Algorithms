//-----------------------------------------------------
// Title: Edge class
// Author: Doðukan Us
// ID: 47113751436
// Section: 03
// Assignment: 2
// Description: This class is to define the structure of weighted edge object inspired from textbook
//-----------------------------------------------------

public class Edge implements Comparable<Edge> {
	
	private final int v, w, weight; //vertices of the edge and the weight of the edge
	
	//constructor that initialize the values with the values given as parameter
	public Edge(int v, int w, int weight){
		 
		this.v = v;
		this.w = w;
		this.weight = weight;
	
	}
	
	//returns one of the vertices
	public int either(){ 
		return v; 
	}
	
	//returns the other vertex
	public int other(int vertex){
		
		if (vertex == v) {
			return w;
		}
		else return v;
	
	}
	
	//returns the weight of the edge
	public int weight() {
		return this.weight;
	}
	
	
	//compare 2 edges according to their weights
	public int compareTo(Edge that){
		
		if (this.weight < that.weight) {
			return -1;
		}
		else if (this.weight > that.weight) {
			return +1;
		}
		else return 0;
	
	}
	
	//returns all information about the edge
	public String toString() {
		return "" + this.either() + "-" + this.other(this.either()) + " : " + this.weight();
	}
} 