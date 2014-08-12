import java.io.File;
import java.io.FileNotFoundException;
import stdlib.In;
import stdlib.StdIn;
import stdlib.StdOut;

public class HandHistoryReader {
	public static void main(String [] args) {
		int i, j, k, l, m, n;
		int index = 0;
		double player0Ledger = 0.0;
		double player1Ledger = 0.0;
		double player2Ledger = 0.0;
		double player3Ledger = 0.0;
		double player4Ledger = 0.0;
		double player5Ledger = 0.0;
		double player6Ledger = 0.0;
		double player7Ledger = 0.0;
		double player8Ledger = 0.0;
		double player9Ledger = 0.0;
		
		String winner;
		String[] playerNames = new String[10];
		StdOut.print("Enter your file path: ");
		String textSource = StdIn.readLine();
		StdIn.fromFile(textSource);
		String text = StdIn.readAll();
		String[] tokens = text.split("\\s+");
		System.out.println("The date this hand was played on is " + tokens[3]);
		System.out.println();
		System.out.println("The stakes being played are: " + tokens[13]);
		System.out.println();
		
		for(i =0; i < tokens.length; i++) {
			if(tokens[i].equals("wins") && tokens[i+1].equals("Pot"))
				break;
		}
		
				i--;
			  System.out.println(tokens[i] + " won " + tokens[i+3]);
			  winner = tokens[i];
			  System.out.println();
			  
			  
		for(j = 0; j < tokens.length; j++) {
			if(tokens[j].equals("Rake"))
				break;
		}
		
			  System.out.println("The amount of rake the site took on this hand was " + tokens[j+1]);
			  System.out.println();
			  
		for(k = 0; k < tokens.length; k++) {
			if(tokens[k].equals("Seat")) {
				playerNames[index] = tokens[k+2];
					index++;
			}
		}
		//56
		StdIn.fromFile(textSource);
		String textByLine = StdIn.readLine();
		String[] textByLineSplit = textByLine.split("\\s+");
		
				  //System.out.println(valueOf(textByLineSplit[56]));
					  
		}
}
