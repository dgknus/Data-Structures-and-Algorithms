//-----------------------------------------------------
// Title: Main class
// Author: Doðukan Us
// ID: 47113751436
// Section: 03
// Assignment: 2
// Description: This class contains the main method and runs the program.
//-----------------------------------------------------

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	
	//main method
	public static void main(String[] args) {
		
		String input; //to hold the path of input file
		String source; //the source city to begin with
		String dest; //the destination city to arrive
		String [][] lineInfo; //hold the tokens of read line seperated by ','
		int visits; //number of visits
		int totalLength = 0; //total route length
		int [] path; // path through the source to destination
		ArrayList<String> lines = new ArrayList<String>(); //hold the lines of input which read line by line
		ArrayList<Edge> pathEdges = new ArrayList<Edge>(); //hold the path's edges returned from function
		
		
		
		//Scanner object to get input
		Scanner scan = new Scanner(System.in); 
		//reading the path of input file
		input = scan.next();
		
		//Reading file
		try {
			//scanner object to read the file
			Scanner scfile = new Scanner(new File(input));
			
			//reading line by line and store it in line string
			while (scfile.hasNext()) {
				
				//add the read line to list
				lines.add(scfile.nextLine());
				
			}
					scfile.close(); //closing the scanner object since it will be no longer used
		// throw an exception if the file cannot be found	
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		//initialize the array with the size of number of lines
		lineInfo = new String[lines.size()][3];
		
		//inserts the tokens to the array
		for(int i = 0; i < lines.size(); i++) {
			
			//splits the line and inserts the tokens to the array
			lineInfo[i] = lines.get(i).split(",");
			
		}
		
		//IndexTable object to convert string to int according to their indexes at the table
		IndexTable t = new IndexTable();
		
		//inserting the first two elements of the line which are cities
		for (String [] s : lineInfo) {			
			t.put(s[0]);
			t.put(s[1]);	
		}
		
		//creating a new weighted graph with the size of number of cities
		WeightedGraph g = new WeightedGraph(t.size());
		
		for (String [] s: lineInfo) {
			// creating an edge with the info read from the input line
			Edge e = new Edge(t.encode(s[0]), t.encode(s[1]), Integer.valueOf(s[2]));
			//adds the edge to the graph
			g.addEdge(e);
			
		}
		
		//reading input from user
		source = scan.next();
		dest = scan.next();
		visits = scan.nextInt();
		
		//creating a Dijkstra object to calculate route from source city
		DijkstraSP dj = new DijkstraSP(g, t.encode(source));
		
		//if user wants to visit another city or cities
		if(visits > 0) {
			String [] toVisit = new String [visits]; //to hold the cities that user wants to visit
			
			for(int i = 0; i < visits; i++) {
				//reading the input of cities to visit
				toVisit[i] = scan.next();
			}
			
			//we visit the first city on the toVisit list
			totalLength += dj.distTo(t.encode(toVisit[0])); //add the distance through that city to the total length
			Edge [] temp = dj.pathTo(t.encode(toVisit[0]));
			for(Edge e: temp) {
				pathEdges.add(e); //add path through the first city to visit
			}
			
			//update current source city to the city that we visit, so we continue our route from that city
			String currentSource = toVisit[0];
			String currentDest; 
			
			//if there are more than one city to visit, visit remaining cities
			for(int i = 1; i < visits; i++) {
				
				//calculate path from the current source city
				dj = new DijkstraSP(g, t.encode(currentSource));
				currentDest = toVisit[i]; //next city to visit is our current destination
				totalLength += dj.distTo(t.encode(currentDest)); //add the distance to the total length of the route
				//add the path from current source to current destination
				Edge [] temp2 = dj.pathTo(t.encode(toVisit[i]));
				for(Edge e: temp2) {
					pathEdges.add(e);
				}
				currentSource = toVisit[i]; //update current source
				
			}
			
			//lastly, visit the destination city from last city we visited
			dj = new DijkstraSP(g, t.encode(currentSource));
			totalLength += dj.distTo(t.encode(dest)); //add the distance to the total length of the route
			//add the path from current source to the main destination
			Edge [] temp3 = dj.pathTo(t.encode(dest));
			for(Edge e: temp3) {
				pathEdges.add(e);
			}
			
		}
		else {
			//if there are no city to visit between source and destination
			
			//simply calculate the path from source to destination and add the properties to the corresponding list
			totalLength = dj.distTo(t.encode(dest));
			Edge [] temp = dj.pathTo(t.encode(dest));
			for(Edge e: temp) {
				pathEdges.add(e);
			}
			
		}
		
		//initializing the path array with the size of the number of edges in the path incremented by one
		path = new int[pathEdges.size()+1];
		
		//holding the current vertex we are at
		int currentVertex = 0;
		
		//check which vertex of the first edge is our source city, so we can insert it to the array in order
		if(pathEdges.get(0).either() == t.encode(source)) {
			path[0] = pathEdges.get(0).either();
			path[1] = pathEdges.get(0).other(pathEdges.get(0).either());
			currentVertex = pathEdges.get(0).other(pathEdges.get(0).either());
		}
		else if(pathEdges.get(0).other(pathEdges.get(0).either()) == t.encode(source)) {
			path[1] = pathEdges.get(0).either();
			path[0] = pathEdges.get(0).other(pathEdges.get(0).either());
			currentVertex = path[1] = pathEdges.get(0).either();
		}
		
		// add the remaining edge verticies
		for(int i = 2; i < path.length; i++) {
			path[i] = pathEdges.get(i-1).other(currentVertex);
			currentVertex = path[i];
		}
		
		// printing out the route and the route length as output
		System.out.println("Routes are:");
		
		for(int i = 0; i < path.length -1; i++) {
			System.out.print(t.decode(path[i]));
			System.out.print("-");
		}
		System.out.println(t.decode(path[path.length-1]));
		
		
		System.out.println("Length of route is: " + totalLength);
		
		//closing the scanner object
		scan.close();
	}

}
