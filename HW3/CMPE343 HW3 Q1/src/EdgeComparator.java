//-----------------------------------------------------
// Title: Edge Comparator class
// Author: Doðukan Us
// ID: 47113751436
// Section: 03
// Assignment: 3
// Description: This class defines a comparator that compares edges according to their weights
//-----------------------------------------------------

import java.util.Comparator;

public class EdgeComparator implements Comparator<Edge>{

	@Override
	public int compare(Edge a, Edge b) {
		//--------------------------------------------------------
		// Summary: compares 2 cities given as parameter
		// Precondition: a and b are Edge
		// Postcondition: returns info about which one is the edge with smaller weight 
		//--------------------------------------------------------
		
		// if first edge has smaller weight
		if(a.weight() < b.weight()) {
			return -1;
		}
		// if second edge has smaller weight
		if(a.weight() > b.weight()) {
			return 1;
		}
		//if both edges have the same weight
		return 0;
		
	}

}
