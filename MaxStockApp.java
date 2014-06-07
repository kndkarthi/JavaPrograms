package hw5solutions;

import java.io.File;

import java.io.FileNotFoundException;
import java.util.*;

import stdlib.In;
import stdlib.StdIn;
import stdlib.StdOut;

//name: Terry Schmidt, ID#: 1433009, CSC402
//this program will ask for a number, and print that many of the highest Dow Jones Industrial closing averages from a file that contains all averages.

public class MaxStockApp {

	public static void main(String[] args) {
		StdOut.println("This program finds the N highest closing averages in a file of Dow Jones Industrial Average closing records. "); //explain program
		StdOut.print("Stock file: "); // ask for file
		String filePath = StdIn.readLine(); //store file path
		StdOut.print("Highest N closing averages. N: "); // ask for N
		int N = StdIn.readInt(); //read in and store N
		final MyMinPQ<Djia> pq = new MyMinPQ<Djia>();

		final File in = new File(filePath);
		try {

			if (!in.exists()) { //if the path we were given is wrong
				StdOut.println("Unable to open the text source " + filePath); //tell the user it's wrong
				System.exit(1); //exit
			}
			Scanner inputStream = new Scanner(in);
			while (inputStream.hasNext()) { //while there's more input to be read
				String fileText = inputStream.nextLine(); //store it in this string
				String[] fields = fileText.split("\\s*,\\s*"); //split the string based on where the comma is
				String date = fields[0]; // the first part of the split is the date, placed at element 0 of the array fields
				double closing = Double.parseDouble(fields[1]); // the second part of the split is the average, placed at element 1 of the array fields
				Djia tmp = new Djia(date, closing); //new Djia object
				
				pq.insert(tmp); //insert the new Djia object
				
				
			}
			inputStream.close(); //close the scanner to remove resource leak
		} catch (FileNotFoundException e) {

		}
		Djia max; //used to store Djia's for later printing
		for(int i = pq.size(); i > N; i--) {
			if(!pq.isEmpty()){ //check that it's not empty
			pq.delMin(); //delete the minimum
			}
		}
		
		for(int j = 0; j < N; j++) {
			if(!pq.isEmpty()){ //check that it's not empty, and that we're not being asked to print more entries than there are in the file
			max = pq.delMin(); //store the lowest Djia that remains for printing
			StdOut.println(max); //print it
			}
		}
		
	}
}
