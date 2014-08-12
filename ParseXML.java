import java.util.ArrayList;


import algs13.Stack;
import stdlib.StdIn;
import stdlib.StdOut;

public class ParseXML {
	
	public static void main(String[] args) {
		StdOut.print("Please enter the path of an XML file: ");  
		String input = StdIn.readString();  // get the input to work with
		StdIn.fromFile(input);
		
		
		ArrayList<String> words = new ArrayList<String>(); // each line of the file will go into this array
		while (!StdIn.isEmpty()) {
			String value = StdIn.readLine(); //take the first line of the file, put it into "value"
			words.add(value);
		}
		
		Stack<String> stackOfStrings = new Stack<String>();  // this holds the text "closing", "opening", "self closing tag"
		String indentSpacing = "    ";	// we need to add indenting for opening tags, and decrement indenting when we find a closing tag.
		int indentLevel = 0;  
		
		for (String word: words) {
			if (tag(word) == "opening") {
				
				System.out.printf("\n" + repeatingString(indentSpacing, indentLevel) + word + "\n");
				
				String nextWord = word.substring(1, (word.length() -1));
			
				stackOfStrings.push(nextWord); 
				
				indentLevel++;  //increment indentlevel by 1
			}
			
			else if (tag(word) == "self closing tag") {
				System.out.printf("\n" + repeatingString(indentSpacing, indentLevel) + word + "\n");
			}
			
			else if (tag(word) == "closing") {
				
				indentLevel--;  //decrement indentlevel by 1
				System.out.printf("\n" + repeatingString(indentSpacing, indentLevel) + word + "\n");  //prints the tag on a new line, with correct spacing
				
				if (stackOfStrings.isEmpty()) {
					System.out.println("a closing tag has no matching opening tag " + stackOfStrings);
					return;
				}
				
				String tagName = stackOfStrings.pop(); //takes the < and /> off the closing word
				String cWord = word.substring(2, (word.length() -1));  // figure out if this is the last name in the stack
				
				
				if (!(tagName.contains(cWord) ) && !(tagName.indexOf("/") == (tagName.length() -1))) {
					System.out.println("a closing tag doesn't have a matching opening tag " + cWord + tagName);
					return;
				}

			}
				else {
				System.out.printf(repeatingString(indentSpacing, indentLevel) + word + "\n");
				// if the program reaches this point, it was supplied with something that was not a tag
			}
		}
		
		if (!(stackOfStrings.isEmpty())) {
			System.out.println("There are opening tags without closing tags left in the stack " + stackOfStrings);
			return;
			// if the input file given is not properly formatted XML, this will be true
		}
		
	}

	
	private static String repeatingString(String space, int counter) {
	    String correctedSpaces = "";
	   
	    for (int i = 0; i < counter; i++) {
	    	correctedSpaces = correctedSpaces + space;
	    }
	    return correctedSpaces;
	}
	
	public static String tag (String word) {
		//this is going to index the string to figure out if the first and last characters are angle brackets.
		if (word.charAt(0) == '<' && word.charAt(word.length() -1) == '>') {
			
			if (word.indexOf("/") == 1) {
				return ("closing");
			}
			else if(word.indexOf("/") == (word.length() -2)) {
				return ("self closing tag");
			}
			else {
				return ("opening");
			}
		}
		    else {
		    	return ("not a tag");
			}		
	}	
	
}
