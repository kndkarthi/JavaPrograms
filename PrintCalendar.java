import stdlib.StdIn;

import stdlib.StdOut;
import week4examples.PrimeNumber;
import algs12.Date;

public class PrintCalendar {

	public static void main(String[] args) {

		StdOut.print("Enter a starting date: ");
		String begin = StdIn.readLine();
		
		StdOut.print("Enter an ending date: ");
		String end = StdIn.readLine();
		
		Date e = new Date(end);
		
		
		int itemCount = 0;
		final int itemCountLimit = 7;
		for (Date d = new Date(begin); (d.isBefore(e)); d = d.next()) {
			StdOut.print(d + "\t");
			itemCount++;
			if (itemCount % itemCountLimit == 0) {
				StdOut.println();
			}
		}
		StdOut.print(e);
	}
}
