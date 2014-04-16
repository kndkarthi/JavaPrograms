package hw2Solutions;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import stdlib.StdIn;
import stdlib.StdOut;

//name: Terry Schmidt, ID number: 1433009

public class TwoSumZero {
	public static void main(String[] args) throws FileNotFoundException {
		System.out.println("This program reads a file of integers - positive, negative, 0 - and determines all pairs of entries with sum 0."); // explain program
		System.out.println("");
	    Scanner in = new Scanner(System.in);  // new scanner object
	    String fname;
	    System.out.printf("Enter file name: "); // prompt user
	    fname = in.next();
	    
	    Scanner infile;
	    
	    try { // get input
	    infile = new Scanner(new File(fname));
	    }
	    	finally { }
	    
	    int nums[] = new int[100];  // declare array of ints of size 100
	    
	    
	    int count = 0;  // variable for keeping track of how many integers are read
	    int i = 0; // variable for indexing
	    int j = 0; // variable for indexing
	  
	    
	    while(infile.hasNextInt()) { // this while loop populates the array
	    	nums[i] = (infile.nextInt());
	    	count++;
	    	i++;
	    }
	     for(i = 0; i < count; i++) {  // iterate over nums array
	    	 for(j = i + 1; j < count; j++) {  // iterate over nums array
	    		 if(nums[i] + nums[j] == 0) {  // if 2 elements of the array sum to 0...
	    			 System.out.println("nums[" + i + "]" + " +" + " nums[" + j + "] = " + nums[i] + " + " + nums[j] + " = 0");  // print it out
	    		 }
	    	 }
	     }
	    
	       System.out.println("There were " + count + " integers read");  //print count
	 
	    
	    infile.close();  //close scanner
	    in.close();  //close scanner
	}
}
