package assign1due416;

import java.util.Scanner;


import java.util.*;

import stdlib.StdIn;
import stdlib.StdOut;
//name: Terry Schmidt, ID number: 1433009

public class Ordered {

	public static void main(String[] args) {
		System.out.print("Enter three integers: ");  //prompt the user
		String integers = StdIn.readLine();  //accept data from user
		
		StringTokenizer threeNumbers = new StringTokenizer(integers);
		String firstNumber;
		String secondNumber;
		String thirdNumber;
		
		firstNumber = threeNumbers.nextToken();  // stores the first token entered in a string called firstNumber
		secondNumber = threeNumbers.nextToken();  //store the second token entered in a string called secondNumber
		thirdNumber = threeNumbers.nextToken();  //store the third token entered in a string called thirdNumber
		
		int number1;
		int number2;
		int number3;
		
		number1 = Integer.parseInt(firstNumber);  //this parses the first token as an integer
		number2 = Integer.parseInt(secondNumber);  //this parses the second token as an integer
		number3 = Integer.parseInt(thirdNumber);  //this parses the third token as an integer
		
		
		// if statement will ensure that true only gets printed if all numbers are ascending, or all numbers are descending, otherwise it will print false.
		if (((number1 < number2) && (number2 < number3)) || ((number1 > number2) && (number2 > number3))) {
			System.out.println("True");
		} 
		else {
				System.out.println("False");
				}
		}
}
