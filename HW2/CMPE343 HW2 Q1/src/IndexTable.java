//-----------------------------------------------------
// Title: Route Finder class
// Author: Doðukan Us
// ID: 47113751436
// Section: 03
// Assignment: 2
// Description: This class is to store the String values of cities and links them with their index number on the list
//-----------------------------------------------------

import java.util.ArrayList;
public class IndexTable {
	
	//List to store cities and return their indexes.
	private ArrayList<String> t;
	
	//Constructor
	public IndexTable() {
		//--------------------------------------------------------
		// Summary: Constructor
		// Precondition: none
		// Postcondition: List t is initialized.
		//--------------------------------------------------------		
		t = new ArrayList<String>();
	}
	
	//If S is not in the list, adds it to the list.
	public void put(String s) {
		//--------------------------------------------------------
		// Summary: Adds the city to the list if it's not in the list yet
		// Precondition: s is a String
		// Postcondition: String s is added to the list if it's not in the list
		//--------------------------------------------------------		
		
		if (t.indexOf(s) == -1) {
			t.add(s);
		}
	}
	
	//Returns the index of the S
	public int encode(String s) {
		//--------------------------------------------------------
		// Summary: Returns the index of the city given in parameter
		// Precondition: s is a String
		// Postcondition: Index of the String s in the list t is returned
		//--------------------------------------------------------		
		return t.indexOf(s);
	}
	//Returns the String at given index
	public String decode(int i) {
		//--------------------------------------------------------
		// Summary: Takes the index of the city as parameter and returns is String value
		// Precondition: i is an integer
		// Postcondition: String value of the element in the i^th element is returned
		//--------------------------------------------------------	
		return t.get(i);
	}
	
	//Returns the size of the list
	public int size() {
		//--------------------------------------------------------
		// Summary: To access to the size of the private list
		// Precondition: none
		// Postcondition: Size of the list t is returned
		//--------------------------------------------------------	
		return t.size();
	}
}
