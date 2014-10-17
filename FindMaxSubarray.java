package WarmUp;

import java.util.Random;

// Terry Schmidt, October 2014

public class FindMaxSubarray {
	public static void main(String[] args) {
		int[] intArray = new int[100];
		
		for(int i = 0; i < intArray.length; i++) {
			intArray[i] = randInt(-100, 100);
			System.out.println("intArray[" + i + "]" + "  =  " + intArray[i]);
		}
		
		System.out.println("");
		System.out.println("");
		maxSubArray(intArray);
	}
	
	
	public static void maxSubArray(int[] inputArray) {

	    int maxStartIndex = 0;
	    int maxEndIndex = 0;
	    int maxSum = Integer.MIN_VALUE; 

	    int cumulativeSum = 0;
	    int maxStartIndexUntilNow = 0;

	    for (int currentIndex = 0; currentIndex < inputArray.length; currentIndex++) {

	        int eachArrayItem = inputArray[currentIndex];

	        cumulativeSum += eachArrayItem;

	        if(cumulativeSum > maxSum) {
	            maxSum = cumulativeSum;
	            maxStartIndex = maxStartIndexUntilNow;
	            maxEndIndex = currentIndex;
	         }
	        
	        if (cumulativeSum < 0) {
	            maxStartIndexUntilNow  = currentIndex + 1;
	            cumulativeSum = 0;
	        }
	    }
	    
	    System.out.println("Max sum: " + maxSum);
	    System.out.println("Starting index of the max subarray: " + maxStartIndex);
	    System.out.println("Ending index of the max subarray: " + maxEndIndex);
	}
	
	public static int randInt(int min, int max) {
	    Random rand = new Random();
	    int randomNum = rand.nextInt((max - min) + 1) + min; // nextInt is normally exclusive of the top value, so add 1 to make it inclusive
	    return randomNum;
	}
}
