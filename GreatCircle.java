package assign1due416;

import java.util.Scanner;
import java.util.*;
import stdlib.StdIn;
import stdlib.StdOut;
import   java.lang.Math.*;

//name: Terry Schmidt, ID Number: 1433009

public class GreatCircle {
		public static void main(String[] args) {
			System.out.print("Enter latitude and longitude of two points: "); //prompt the user
			String input = StdIn.readLine(); // get the input
			StringTokenizer points = new StringTokenizer(input); //tokenize the input based on where spaces are
			
			double lat1 = Double.parseDouble(points.nextToken()); // read the first token as a double
			double long1 = Double.parseDouble(points.nextToken()); // read the second token as a double
			double lat2 = Double.parseDouble(points.nextToken()); // read the third token as a double
			double long2 = Double.parseDouble(points.nextToken()); // read the fourth token as a double
			double d = distance(lat1, long1, lat2, long2); // this invokes the distance method below, and supplies it with the 4 doubles
			
			System.out.println("Distance is " + d + " miles");
		}
		
		// this is the method that calculates the distance of two points on Earth, based on their latitude and longitude
		// it takes 4 doubles as arguments, and uses methods in the Math class to compute the distance in miles
		public static double distance(double x0, double y0, double x1, double y1) {
			double R = 3950.02;
			double d = R* Math.acos(Math.sin(Math.toRadians(x0))*Math.sin(Math.toRadians(x1)) + 
					Math.cos(Math.toRadians(x0))*Math.cos(Math.toRadians(x1))*Math.cos(Math.toRadians(y0 - y1)));
			return d;
			
		}
}
