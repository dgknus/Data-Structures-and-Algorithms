//-----------------------------------------------------
// Title: Route Finder class
// Author: Doðukan Us
// ID: 47113751436
// Section: 03
// Assignment: 2
// Description: This class is to find the available paths in given number of hops and return it to the main function.
//-----------------------------------------------------

import java.util.ArrayList;
import java.util.Arrays;

public class RouteFinder {
	
	int hops; //number of hops
	ArrayList<int[]> routes; //Arraylist to store the available routes
	int [] route;	//Array to store a single route
	
	//Constructor
	public RouteFinder(int h) {
		//--------------------------------------------------------
		// Summary: Constructor
		// Precondition: h is an integer
		// Postcondition: routes list, route array and hops integer are initialized.
		//--------------------------------------------------------		
		routes = new ArrayList<int[]>();
		hops = h;
		route = new int [hops+1]; //hops+1 because 2 edges connects 3 vertices
	}
	
	/*The function called from the main method. It has 2 parameters
	 * The directed graph we work with, and the start vertex.
	 * It uses this parameters and calls paths function to calculate routes.
	 * returns the routes list
	 */
	public ArrayList<int[]> findRoute(Digraph g, int v) {
		//--------------------------------------------------------
		// Summary: Calls the paths function and returns the calculated routes
		// Precondition: v is an integer and g is a Digraph
		// Postcondition: All calculated routes are returned in a list
		//--------------------------------------------------------		
		
		route[0] = v;
		
		paths(v, g, 0);
				
		return routes;
		
	}
	
	/*
	 * it has 3 parameters, the digraph we work on, the vertex we start, and a count value to track the number of recursive function call
	 * it loops over the adjecent vertices of start vertex and adds them to the next index of the route array
	 * it calls itself recursively until it reach the desired amount of hops which is tracked by the count value
	 * it calls the function by using increased value of count
	 * when it reaches desired value of hops, it copies the route array to a temporary array and adds it to the routes list
	 * it goes for the each vertices adj to vertice v(start vertex)
	 */
	public void paths(int v, Digraph g, int count) {
		//--------------------------------------------------------
		// Summary: Calculates the routes from a starting vertex and stores it in a list
		// Precondition: v is an integer and g is a Digraph and count is an integer
		// Postcondition: All routes are calculated and stored in routes list
		//--------------------------------------------------------
		if(count < hops) {
			for (int w: g.adj(v)) {
				route[count+1] = w;
				paths(w, g, count+1);
			}
		}
		else{
			route[hops] = v;
			int [] temp = Arrays.copyOf(route, route.length); //temporary array to hold current value of route array
			routes.add(temp); //adds the current route array by using temporary array
		}
	}
	
	
	

}
