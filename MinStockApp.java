package hw5solutions;

import java.io.File;


import java.io.FileNotFoundException;
import java.util.*;

import stdlib.In;
import stdlib.StdIn;
import stdlib.StdOut;

//name: Terry Schmidt, ID#: 1433009, CSC402
//this program will ask for a number, and print that many of the lowest Dow Jones Industrial closing averages from a file that contains all averages.

public class MinStockApp {

	public static void main(String[] args) {
		StdOut.println("This program finds the N lowest closing averages in a file of Dow Jones Industrial Average closing records. "); //explain program
		StdOut.print("Stock file: "); //prompt for file
		String filePath = StdIn.readLine(); // read the path given
		StdOut.print("Lowest N closing averages. N: "); //ask for N
		int N = StdIn.readInt(); // read in and store N
		final MyMinPQ<Djia> pq = new MyMinPQ<Djia>(); 

		final File in = new File(filePath);
		try {

			if (!in.exists()) { //if we're given an incorrect path
				StdOut.println("Unable to open the text source " + filePath); // print this
				System.exit(1); // exit
			}
			Scanner inputStream = new Scanner(in);
			while (inputStream.hasNext()) { // while there's more input to read
				String fileText = inputStream.nextLine(); //fileText = the next line
				String[] fields = fileText.split("\\s*,\\s*"); //split the line based on where the comma is
				String date = fields[0]; // put the first part of the split in element 0 of fields
				double closing = Double.parseDouble(fields[1]); // put the second part of the split in element 1 of fields
				Djia tmp = new Djia(date, closing); // new Djia object
				pq.insert(tmp); //insert the Djia object on the priority queue
			}
			inputStream.close(); // close scanner to remove resource leak
		} catch (FileNotFoundException e) {

		}
		Djia min; // will hold the N minimum Djia objects for immediate printing
		for(int i = 0; i < N; i++) {
			if(!pq.isEmpty()) { // check that its not empty, and that we're not being asked to print more Djia's than the file holds.
			min = pq.delMin(); //put the lowest Djia into min
			StdOut.println(min); //print min
			}
		}
	}
}
