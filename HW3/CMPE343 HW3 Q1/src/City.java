//-----------------------------------------------------
// Title: City class
// Author: Doðukan Us
// ID: 47113751436
// Section: 03
// Assignment: 3
// Description: This class defines the structure and properties of a city object
//-----------------------------------------------------

public class City {
	
	private char name; //name of the city
	private int x, y; //coordinates of the city
	
	public City(char name, int x, int y) {
		//--------------------------------------------------------
		// Summary: constructor that initializes its name and 
		// 			coordinates according to the parameter
		// Precondition: name is char, x and y are integer
		// Postcondition: name, x and y are initialized
		//--------------------------------------------------------
		
		this.name = name;
		this.x = x;
		this.y = y;	
	}
	
	public char name() {
		//--------------------------------------------------------
		// Summary: returns the name of the city
		// Precondition: none
		// Postcondition: name of the city is returned
		//--------------------------------------------------------
		return this.name;
	}
	
	//returns the x coordinate of the city
	public int x() {
		//--------------------------------------------------------
		// Summary: returns the x coordinate of the city
		// Precondition: none
		// Postcondition: x coordinate of the city is returned
		//--------------------------------------------------------
		return this.x;
	}
	
	public int y(){
		//--------------------------------------------------------
		// Summary: returns the y coordinate of the city
		// Precondition: none
		// Postcondition: y coordinate of the city is returned
		//--------------------------------------------------------
		return this.y;
	}

}
