package assign1due416;

import java.util.Scanner;


import java.util.*;

import stdlib.StdIn;
import stdlib.StdOut;

// name: Terry Schmidt, ID number: 1433009
public class HiFour {

		public static void main(String[] args) {
			
			System.out.print("Enter four names: ");  // prompt user
			String fournames = StdIn.readLine();   // get input
			
			StringTokenizer names = new StringTokenizer(fournames);  //split the input string into "tokens", each of which is a name
			
			/*System.out.print("There are ");  // this prints out the amount of tokens
			System.out.print(names.countTokens());
			System.out.print(" tokens.");
			System.out.print("\n");
			System.out.print("\n");*/
			
			int indx = 3;  //this is initialized at the last element of the array in order to reverse it, then iterates through by decrementing
			String reverseNames[] = new String[4];  // creates an array of strings/tokens
			while (names.hasMoreTokens()) {
				reverseNames[indx] = names.nextToken();
				indx--;
			}
			
			System.out.print("Hi ");
			for (indx = 0; indx < 3; indx++) {  // since the previous paragraph of code reversed the names by adding names from the end of the array
				                                              // we can now just iterate forward through the array, as normal
				System.out.print(reverseNames[indx] + ", ");  // prints the first 3 names via the for loop
			}
			    System.out.print("and " + reverseNames[3] + ".");  // prints the last name
		}
}
