package assignment3solutions;

import stdlib.StdIn;
import stdlib.StdOut;

// Terry Schmidt, ID 1433009

public class LetterFrequencies {

	public static void main(String[] args) {
		StdOut.print("Enter the pathname or URL of a book: ");
		String input = StdIn.readString();
		StdIn.fromFile(input);
		String words = StdIn.readAll();
		words = words.toLowerCase();	
		
		String alphabet= "abcdefghijklmnopqrstuvwxyz";
		
		int[] count = new int [26]; // how many letters there are to count in our array
		
		// iterate over the alphabet, iterate over words, and compare them!
		for (int i = 0; i < words.length(); i++) {
			for (int j = 0; j < alphabet.length(); j++) {
				if (words.charAt(i) == alphabet.charAt(j)) {
					count[j]++;
				}
			}
		}
		for (int z = 0; z < count.length; z++) {
			System.out.printf("%c, %d \n",alphabet.charAt(z), count[z]);
		}
			
	  }

	}
