package hw2Solutions;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.awt.Color;
import algs12.Point2D;
import stdlib.StdIn;
import stdlib.StdOut;
import stdlib.*;

//name: Terry Schmidt, ID number: 1433009

public class Point2DApp {
	public static void main(String[] args) {
		System.out.println("This program will draw N random points in a square and then draw a line between two points that are farthest apart."); //explain program to user
		System.out.println("");
		System.out.print("Enter N: ");  // prompt user
		int n = StdIn.readInt();   // get input
	    
		Point2D[] pts = new Point2D[n];  //create an array of Point2D objects of N size
	    
	    StdDraw.setPenColor(StdDraw.RED);  //set the pen color to red
	    StdDraw.setPenRadius(0.008);  //set the pen width
	    for(int i = 0; i < pts.length; i++) {  //this for loop will seed our N sized array with N amount of Point2D objects that have random values, and then draw them
	    	pts[i] = new Point2D(Math.random(), Math.random());
	    	pts[i].draw();
	    }
	    
	    int firstIndex = 0;  // variable for indexing 
	    int secondIndex = 0; // variable for indexing
	    double dist2 = 0.0;  //variable for holding current distance between [i] and [j]
	    double maxDist2 = 0.0;  //variable that stores the biggest current distance
	    int i;
	    int j;
	    
	    for(i = 0; i < pts.length; i++) {  // iterating over the array elements
	    	for(j= i + 1; j < pts.length; j++) {  // iterating over the array elements
	    		dist2 = pts[i].distanceTo(pts[j]);  // store the current distance in dist2
	    		if(dist2 > maxDist2) {  // if the current distance is bigger than the biggest distance
	    			firstIndex = i;  // save this index of the array for later printing
	    			secondIndex = j;  // save this index of the array for later printing
	    			maxDist2 = dist2; // make the biggest distance the current distance
	    		}
	    	}
	    }
	    
	    StdDraw.line(pts[firstIndex].x(), pts[firstIndex].y(), pts[secondIndex].x(), pts[secondIndex].y());  // draw the previously saved elements of the array, which are the longest
	}
}
