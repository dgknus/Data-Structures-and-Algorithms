//-----------------------------------------------------
// Title: Main class
// Author: Doðukan Us
// ID: 47113751436
// Section: 03
// Assignment: 2
// Description: This class contains the main method and runs the program. It also has 2 static methods apart from main method
//-----------------------------------------------------

import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	
	//static variable of DecimalFormat to print out the double values in desired format
	private static final DecimalFormat df = new DecimalFormat("0.0");
	
	//main method
	public static void main(String[] args) throws Exception {

		String input; // to hold the path of the input file
		String [][] cityInfo; // hold the tokens which include information of the city
		ArrayList<String> lines = new ArrayList<String>(); // to hold the lines read from input file
		ArrayList<City> cities = new ArrayList<City>(); // to hold the created cities
		ArrayList<Edge> mstEdgesList = new ArrayList<Edge>(); // list of the mst edges
		Edge [] mstEdges; // array to hold mst edges
		
		// Scanner object to read input from user
		Scanner scan = new Scanner(System.in);
		// reading the path of the input file
		input = scan.next();
		
		//Reading file
		try {
			//scanner object to read the file
			Scanner scfile = new Scanner(new File(input));
			
			//reading line by line and store it in line string
			while (scfile.hasNext()) {
				
				lines.add(scfile.nextLine());
				
			}
					scfile.close(); //closing the scanner object since it will be no longer used
		// throw an exception if the file cannot be found	
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		// initializing the array with the size of the number of lines and 3 which are for the name, x and y coordinates info
		cityInfo = new String [lines.size()][3];
		
		//split the each line by ',' and hold it on array
		for(int i = 0; i < lines.size(); i++) {
			cityInfo[i] = lines.get(i).split(",");
			
			// add the city object with the info from the array and add it to the cities list
			cities.add(new City(cityInfo[i][0].charAt(0), Integer.valueOf(cityInfo[i][1]), Integer.valueOf(cityInfo[i][2])));
		}
		
		// edge weighted graph object with size of the number of cities
		WeightedGraph g = new WeightedGraph(lines.size());
		
		// iterating over cities list
		for(int i = 0; i < cities.size()-1; i++) {
			for(int j = i+1; j < cities.size(); j++) {
				
				//creating an edge from a city to each of the other cities
				Edge e = new Edge((Integer.valueOf(cities.get(i).name()) - 65), (Integer.valueOf(cities.get(j).name()) - 65), calculateDist(cities.get(i),cities.get(j)));
				// adding the edge to the graph
				g.addEdge(e);
				
			}
		}
		
		// calculator object to use its functions
		LeastPoleCalculator lpc = new LeastPoleCalculator(g);
		
		//Queues of edges to hold the calculated edges
		EdgeQueue eq = lpc.calculate();
		
		// as long as the queue contains edge
		while(!(eq.isEmpty())) {
			//add edge to the list of mst edges
			mstEdgesList.add(eq.deQueue());
			
		}
		
		// initializing the array of mst edges with the size of the number of mst edges
		mstEdges = new Edge [mstEdgesList.size()];
		
		// inserting from list to array
		for(int i = 0; i < mstEdgesList.size(); i++) {
			mstEdges[i] = mstEdgesList.get(i);
		}
		
		// sorting the edges in ascending order according to their lengths (weights)
		sortEdges(mstEdges);
		
		//printing out the output
		System.out.println("Paths are:");
		
		
		for(Edge e : mstEdges) {
			
			// printing the vertices of the edge in alphabetical order
			if(e.either() < e.other(e.either())) {
				System.out.print((char) (e.either() + 65));
				System.out.print("-");
				System.out.print((char)(e.other(e.either()) + 65));
			}
			else {
				System.out.print((char)(e.other(e.either()) + 65));
				System.out.print("-");
				System.out.print((char) (e.either() + 65));
			}
			
			System.out.print(": ");
			// printing the total length as "0,0" format
			System.out.println(df.format(e.weight()));
			
		}
		
		//closing the scanner object
		scan.close();
		
	}
	
	
	private static double calculateDist(City a, City b) {
		//--------------------------------------------------------
		// Summary: Calculates the distance between 2 cities by using Euclidean distance formula
		// Precondition: a and b are City
		// Postcondition: Distance between two cities are calculated
		//--------------------------------------------------------
		
		// Euclidean formula
		double dist = Math.sqrt((Math.abs(a.x() - b.x())*Math.abs(a.x() - b.x())) + (Math.abs(a.y() - b.y())*Math.abs(a.y() - b.y())));
		// return the distance
		return dist;
		
	}
	
	private static void sortEdges(Edge [] edges) {
		//--------------------------------------------------------
		// Summary: Sorts the edges according to their weights in ascending order
		// Precondition: edges is Edge array
		// Postcondition: The edges in the edges array are sorted in ascending order by weight
		//--------------------------------------------------------
		
		// iterate over the array
		for(int i = 0; i < edges.length-1; i++) {
			for(int j = i+1; j < edges.length; j++) {
				
				// if lentgth of the edge is bigger than the next edge
				if(edges[i].weight() > edges[j].weight()) {
					
					//swap edges
					Edge temp = edges[i];
					edges[i] = edges [j];
					edges[j] = temp;
					
				}
			}
		}
	}
	

}
