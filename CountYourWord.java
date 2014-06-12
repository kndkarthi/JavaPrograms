package JustPractice;

import java.io.File;
import java.io.FileNotFoundException;
import stdlib.In;
import stdlib.StdIn;
import stdlib.StdOut;

public class CountYourWord {
	public static void main(String[] args) throws FileNotFoundException {
		StdOut.println("This program takes your text file and a word that you enter, and will determine how many times that word occurs in the text file.");
		StdOut.print("Enter your file path: ");
		String textSource = StdIn.readLine();
		StdOut.print("Enter the word you want counted: ");
		String userWord = StdIn.readString();
		final In in = new In(textSource);
		String inputText = in.readAll();
        String[] inputWords = inputText.split("\\s+");
        int counter = 0;
        
        for(int i = 0; i < inputWords.length; i++) {
        	if(inputWords[i].equals(userWord))
        		counter++;
        }
        
		if(counter == 1) {
			System.out.println("Your word " + "'" + userWord + "'" + " appears " + counter + " time in the file.");
		} else {
        System.out.println("Your word " + "'" + userWord + "'" + " appears " + counter + " times in the file.");
		}
	}
}
