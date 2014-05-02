package hw3solutions;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.awt.Color;
import stdlib.*;
import java.util.Scanner;
import java.util.Stack;

/** Description: This program parses parentheses, brackets, and braces from a users text file and figures out if they are balanced or not.
* Author: Terry Schmidt
* Class: CSC402
*/
public class BalancedApp  {

	/** 
	 * Determines if the user supplied text file has balanced parentheses, brackets, and braces.
	 * @prints True if balanced and false if not.
	 * @throws FileNotFoundException if the user enters an incorrect path.
	 */
	
	public static void main(String[] args) throws FileNotFoundException {
		final char leftParen = '(';
		final char rightParen = ')';
		final char leftBrack = '[';
		final char rightBrack = ']';
		final char leftBrace = '{';
		final char rightBrace = '}';
		boolean valid = true;
		
		DelimPos temp;
		
		
		StdOut.print("Please enter the path of a file with parentheses, brackets and braces: ");  // prompt user
		
		Scanner in = new Scanner(System.in);  // create new scanner object to get input
	    String fname;
	    fname = in.next();
	    
	    Scanner infile;
	    
	    	try {  // try block to open the path the user gave
	    		infile = new Scanner(new File(fname));
	    	}
	    	finally { }
		
	    
	    
		Stack<DelimPos> charStack = new Stack<DelimPos>();  // create new stack object to hold characters and linecounts
		
		 infile.useDelimiter("");  // delimiter will help us read 1 character at a time
		 
		 int linecnt = 1; // counter to keep track of lines for printing
		 char ch = 'b';
		 System.out.print("\n" + linecnt + ". ");
   	
   	   while(infile.hasNext()) {  // while there are more characters to process...
   		   String s = infile.next();  
   		   ch = s.charAt(0);  // check the character
  	     
   		   if (ch == '\n' && infile.hasNext()) {  // if the character is a newline and there are more characters to process...
  	    	 linecnt++;  //increment linecnt
  	    	 System.out.print("\n" + linecnt + ". ");  // print out linecnt
   		   }
   		   	else {
  	    	 System.out.print(ch);
  	     
  	     
  	     if (ch == leftParen || ch == leftBrack || ch == leftBrace) {  //if the character is (, [, or {, push it onto the stack
  	     		charStack.push(new DelimPos(linecnt, ch));
  	     	}
  	     if (ch == rightParen || ch == rightBrack || ch == rightBrace) { // if the character is ), ], or } and the stack is empty, then its not balanced...
  	    	 if(charStack.isEmpty()) {
  	    		 valid = false;
  	    		 System.out.println("");
  	    		 //error statement #1: in the case that there is a closing character with no opening character
  	    		System.out.println("Error: Line " + linecnt + " closing character " + "'" + ch + "'" + "," +  " with no matching opening character."); // print error, linecnt, and char
  	    		System.exit(0); // file is not balanced and prints out why, so now its time to exit
  	    	 }
  	    		
  	    	 else {
  	    		 temp = charStack.pop(); // pop the stack, put it into temp
  	    		 if(ch == rightParen && temp.ch == leftParen || ch == rightBrace && temp.ch == leftBrace || ch == rightBrack && temp.ch == leftBrack) {
  	    			 valid = true;
  	    		 }
  	    		 else {
  	    			 valid = false;
  	    			 System.out.println("");
  	    			 //error statement #2: in the case that a closing character does not match the most recent unmatched opening character
  	    			System.out.println("Error: Line " + linecnt + ". Symbol " + "'" + ch + "'" + " is the wrong closing symbol for " + "'" + temp.ch + "'" + temp.linecnt); // print error, line count, the wrong symbol
  	    			System.exit(0); //exit
  	    		 }
  	    	 }
  	     }
   	   }  
   	   }
  	     if(!charStack.isEmpty()) {  // if after matching the character the stack still has characters in it...
  	    	 valid = false; // its unbalanced
  	    	 temp = charStack.pop(); // pop the stock, put it in temp for printing
  	    	 //error #3: for when the program gets to the end of the file but there are still characters on the stack
  	    	 System.out.println("Error: At end of file, no closing symbol found for char = '" + temp.ch + "'" + " line " + temp.linecnt);  //print error, print the char that is left over, and the linecount
  	    	System.exit(0); // exit
  	     } 
  	  
  	System.out.println("");  // if the program gets here, then input is balanced
  	System.out.println("Input is balanced.");
  	   
  	 in.close();  //close scanner to remove resource leak
  	 infile.close(); // close scanner to remove resource leak
	}
}
	
