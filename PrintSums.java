package hw2Solutions;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import stdlib.StdIn;
import stdlib.StdOut;

//name: Terry Schmidt, ID number: 1433009

public class PrintSums {
	public static void main(String[] args) throws FileNotFoundException {
		System.out.println("This program reads an input file of integers and prints the sum of the even integers, the sum of the odd integers, the sum of all integers, and the number of integers read.");
	    System.out.println("");
		Scanner in = new Scanner(System.in);  // create new scanner object to get input
	    String fname; 
	    System.out.printf("Enter file name: "); // prompt user
	    fname = in.next();
	    
	    Scanner infile;
	    
	    try {  // try block
	    infile = new Scanner(new File(fname));
	    }
	    	finally { }
	    
	    int count = 0;  // int variable for storing the total amount of integers
	    int ecount = 0;  // int variable for storing the total amount of even integers
	    int ocount = 0;  // int variable for storing the total amount of odd integers
	    int total = 0;  // int variable for storing the sum of all integers
	    int etotal = 0;  // int variable for storing the sum of even integers
	    int ototal = 0;  // int variable for storing the sum of odd integers
	    int x;  // int variable to hold whatever the current integer is
	    
	    while(infile.hasNextInt()) {  // while loop that checks if there is a next int.  If there is, it puts the nextInt into x, increments count, figures out of its even or odd
	    	                                     // and increments and sums appropriately.
	    	x = (infile.nextInt());
	    	count++;  // keeping track of the count
	    	total += x;  // keeping track of the total of all integers
	    	
	    	if(x % 2 == 0) {
	    		ecount++; // if the current integer is even, we increment the amount of even integers by 1
	    		etotal += x;  // if the urrent integer is even, we add it to the running sum called etotal
	    	}
	    	else {
	    		ocount++; // if the current integer is odd, we increment the amount of odd integers by 1
	    		ototal += x; // if the current integer is odd, we add it to the running sum of odd integers called ototal
	    	}
	    }
	     
	       System.out.println("There were " + count + " integers read");  // print the amount of integers read
	       System.out.println("Sum of the " + ecount + " even integers: " + etotal);  // print the number of even integers read and their total sum
	       System.out.println("Sum of the " + ocount + " odd integers: " + ototal);  // print the number of odd integers read and their total sum
	       System.out.println("Sum of all integers: " + total);  // print the total sum of all integers
	    
	    infile.close();  // close scanner to remove resource leak
	    in.close();  // close scanner to remove resource leak
	}
}
