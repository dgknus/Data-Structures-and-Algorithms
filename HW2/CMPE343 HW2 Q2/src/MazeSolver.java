//-----------------------------------------------------
// Title: Maze Solver class
// Author: Doðukan Us
// ID: 47113751436
// Section: 03
// Assignment: 2
// Description: This class contains main function, it reads input from the user, creates a path finder object and uses it functions to calculate the path
//				than printing out the calculated paths by length in ascending order.
//-----------------------------------------------------

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class MazeSolver {

	public static void main(String[] args) {
		//--------------------------------------------------------
		// Summary: The main function that program runs
		// Precondition: input file containing maze is read from user input
		// Postcondition: calculated paths are printed in ascending order by length
		//--------------------------------------------------------	
		
		ArrayList<Character> maze = new ArrayList<Character>(); // list to hold the elements of the maze read from user input
		ArrayList<Integer> treasures = new ArrayList<Integer>(); // list of treasures in the given maze
		ArrayList<int[]> pathsInInt; // list of paths as integers which are indexes of characters in maze list
		String[] paths; // array of paths to store paths as String
		
		String input; // to hold the path of input file read from user input
		String line; // to read the file line by line while reading the file
		
		Scanner scan = new Scanner(System.in); // scanner object to read input from the user
		
		input = scan.next(); // reading the path of the input file
		
		//Reading file
		try {
			//scanner object to read the file
			Scanner scfile = new Scanner(new File(input));
					
			//reading line by line and store it in line string
			while (scfile.hasNext()) {
				line = scfile.nextLine();
				for(int i = 0; i < line.length(); i++) {
					maze.add(line.charAt(i)); //adding the characters in the line to the maze list
				}
			}
					scfile.close(); //closing the scanner object since it will be no longer used
		// throw an exception if the file cannot be found	
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		// finding the treasures in the maze and hold it in a list
		for(int i = 0; i < maze.size(); i++) {
			if(maze.get(i) == 'E') {
				treasures.add(i);
			}
		}
		
		// creating a directed graph with the size of the number of characters in the maze
		Digraph g = new Digraph(maze.size());
		
		// adding edges if the next, behind, upward or downward character is not a wall character
		for(int i = 0; i < maze.size(); i++) {
			if((maze.get(i) != '-') && (maze.get(i) != '+') && (maze.get(i) != '|')) { //if the vertex is not a wall character
				
				// if it's the entrance vertex to the maze
				if(i % 16 == 0) {
					g.addEdge(i, i+1); // adding an edge to the character next to it
				}
				// if the vertex is between the walls
				else if((i % 16 > 0) && (i % 16 < 15)) { 
					
					// if next character is not a wall character
					if((maze.get(i+1) != '-') && (maze.get(i+1) != '+') && (maze.get(i+1) != '|')) {
						g.addEdge(i, i+1);
					}
					// if behind character is not a wall character
					if((maze.get(i-1) != '-') && (maze.get(i-1) != '+') && (maze.get(i-1) != '|')) {
						g.addEdge(i, i-1);
					}
					// if upward character is not a wall character
					if((maze.get(i-16) != '-') && (maze.get(i-16) != '+') && (maze.get(i-16) != '|')) {
						g.addEdge(i, i-16);
					}
					// if downward character is not a wall character
					if((maze.get(i+16) != '-') && (maze.get(i+16) != '+') && (maze.get(i+16) != '|')) {
						g.addEdge(i, i+16);
					}	
				}
			}
		}
		
		//creating a PathFinder object to use its functions
		PathFinder pf = new PathFinder(treasures);
		
		// calling the function and storing the calculated paths which are in integer list form, in a integer list
		pathsInInt = pf.findPaths(g, 16);
		
		// initializing the paths string array with the same size as the paths in integer list
		paths = new String [pathsInInt.size()];
		
		// loop for inserting the path to the string array
		for (int i = 0; i < pathsInInt.size(); i++) {
			String s = ""; // empty string to start with
			for (int k : pathsInInt.get(i)) { //for each element of the paths in in list, which are indexes of maze list
				s += maze.get(k); // get the k^th element of the maze and add it to the string
			}
			paths[i] = s; // store the string in paths array
		}
		
		//sorting the unsorted array in ascending order by length
		sortArray(paths);
		
		// printing out the number of paths
		System.out.println(paths.length + " treasures are found.");
		
		// if there is a path through the treasure, print the paths
		if(paths.length > 0) {
			
			System.out.println("Paths are:");
			// iterate over paths array and print each string in it
			for(int i = 0; i < paths.length; i++) {
				System.out.println((i+1) + ") " + paths[i]);
			}
			
		}
		
		scan.close(); //closing the scanner object
			
	}
	
	
	
	public static void sortArray(String[] s) {
		//--------------------------------------------------------
		// Summary: Static funtion to sort a given array in parameter
		// Precondition: s is a String array
		// Postcondition: Array s is sorted in ascending order by length
		//--------------------------------------------------------
		
		//iterate over the string s
		for(int i = 0; i < s.length-1; i++) {
			// if the string is longer than the next string
			if(s[i].length() > s[i+1].length()) {
			
				//switch elements
				String temp = s[i];
				s[i] = s[i+1];
				s[i+1] = temp;
			}
		}
	}
	
	
}
