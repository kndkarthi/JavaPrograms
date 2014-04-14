package assignment1solutions;

import stdlib.StdIn;
import stdlib.StdOut;

// Terry Schmidt, ID 1433009

public class FindGCD {
	public static long euclid(int a, int b) {
		if (b == 0) {
			return a;
		}
		return euclid(b, a%b);
	}
	
	public static void main(String[] args) {
		StdOut.print("Enter a positive integer: ");
		int a = Integer.parseInt(StdIn.readLine());
		StdOut.print("Enter another positive integer: ");
		int b = Integer.parseInt(StdIn.readLine());
		StdOut.printf("The greatest common divisor is: %d.", euclid(b,a%b));
	}
}
