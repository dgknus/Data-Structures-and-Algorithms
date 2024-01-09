//-----------------------------------------------------
// Title: Flight Network class
// Author: Doðukan Us
// ID: 47113751436
// Section: 03
// Assignment: 2
// Description: This class contains main method and runs the program
//-----------------------------------------------------

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
public class FlightNetwork {

	public static void main(String[] args) {
		//--------------------------------------------------------
		// Summary: Main method that runs the program
		// Precondition: none
		// Postcondition: All possible routes are calculated according to the user input and output is printed
		//--------------------------------------------------------	
		
		ArrayList<String> lines = new ArrayList<String>(); //List to store lines of input file
		ArrayList<int[]> routes; //List to store routes calculated
		String input; //to get the path of the input file from user input
		int hops; 	//to get number of hops from user input
		String start; //to get start vertex from user input
		
		//scanner object to read input
		Scanner scan = new Scanner(System.in);
		
		//reading input
		input = scan.next();
		hops = scan.nextInt();
		start = scan.next();
		
		
		//Reading file
		try {
			//scanner object to read the file
			Scanner scfile = new Scanner(new File(input));
			
			//reading line by line and store it in lines list
			while (scfile.hasNext()) {
				lines.add(scfile.nextLine().replaceAll("\\s",""));
			}
			scfile.close();
		// throw an exception if the file cannot be found	
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		
		//split the line to get the cities individualy which are seperated by ',' and store it in 2 dimensional edges array
		String [][] edges = new String [lines.size()][hops];
		int k = 0;
		for (String w: lines) {
			edges[k++] = w.split(",");
		}
		
		
		//inserting cities to index table
		IndexTable t = new IndexTable();
		for(int i = 0; i < lines.size(); i++) {
			for(int y = 0; y < 2; y++) {
				t.put(edges[i][y]);
			}
		}
		
		//Creating a directed graph with the size of number of cities
		Digraph G = new Digraph(t.size());
		
		//Creating directed edges
		for(int i = 0; i < lines.size(); i++) {
			G.addEdge(t.encode(edges[i][0]), t.encode(edges[i][1]));
		}
		
		// creating routefinder object to use its functions
		RouteFinder rf = new RouteFinder(hops);
		routes = rf.findRoute(G, t.encode(start));
		
		// prints the output
		System.out.println("Number of total routes: " + routes.size());
		System.out.println("Routes are:");
		
		for(int i = 0; i < routes.size(); i++) {
			for(int j = 0; j < hops+1; j++) {
				System.out.print(t.decode(routes.get(i)[j]));
				if(j != hops)
				System.out.print("-");
			}
			if(i != routes.size()-1) {
				System.out.println();
			}
		}
		
		//close the scanner object
		scan.close();
		
	}
}
