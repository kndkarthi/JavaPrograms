package WarmUp;

import java.util.Random;
import java.lang.*;

// Terry Schmidt, October 2014

public class QuicksortInsertionSortCombo {
	static double logOfIntArrayLength;
	
	 public static void main(String[] args) {
		    int[] intArray = new int[60];
		    logOfIntArrayLength = Math.log(intArray.length);
		    System.out.println(logOfIntArrayLength);
		    System.out.println("");
		    
		    for(int i = 0; i < intArray.length; i++) {
		    	intArray[i] = randInt(-100, 100);
		    }
		    
		    System.out.println("intArray before any sorting: ");
		    for (int i: intArray) {
		      System.out.println(i);
		    }
		    System.out.println("");
		    
		    recursiveQuicksort(intArray, 0, intArray.length - 1);
		    
		    System.out.println("intArray after sorting: ");
		    for (int i: intArray) {
		      System.out.println(i);
		    }
		  }

		  public static void recursiveQuicksort(int[] intArray, int startIndex, int endIndex) {
		    int size = endIndex - startIndex + 1;
		    if (size < logOfIntArrayLength) { // if the array is small
		      insertionSort(intArray, startIndex, endIndex); // use insertion sort
		      return;
		    }
		    else { // otherwise use quicksort
		      double median = median(intArray, startIndex, endIndex);
		      int partition = partition(intArray, startIndex, endIndex, median);
		      recursiveQuicksort(intArray, startIndex, partition - 1);
		      recursiveQuicksort(intArray, partition + 1, endIndex);
		    }
		  }
		  
		  public static void insertionSort(int[] intArray, int left, int right) {
			    int in, out;

			    for (out = left + 1; out <= right; out++) {
			      int temp = intArray[out];
			      in = out;

			      while (in > left && intArray[in - 1] >= temp) {
			        intArray[in] = intArray[in - 1];
			        --in;
			      }
			      intArray[in] = temp;
			    }
			  }

		  public static double median(int[] intArray, int left, int right) {
		    int center = (left + right) / 2;

		    if (intArray[left] > intArray[center])
		      swap(intArray, left, center);

		    if (intArray[left] > intArray[right])
		      swap(intArray, left, right);

		    if (intArray[center] > intArray[right])
		      swap(intArray, center, right);

		    swap(intArray, center, right - 1);
		    return intArray[right - 1];
		  }

		  public static void swap(int[] intArray, int index1, int index2) {
		    int temp = intArray[index1];
		    intArray[index1] = intArray[index2];
		    intArray[index2] = temp;
		  }

		  public static int partition(int[] intArray, int left, int right, double pivot) {
		    int leftPtr = left;
		    int rightPtr = right - 1;
		    while (true) {
		      while (intArray[++leftPtr] < pivot);
		      while (intArray[--rightPtr] > pivot);
		      	if (leftPtr >= rightPtr)
		      		break;
		      	else
		        swap(intArray, leftPtr, rightPtr);
		    }
		    swap(intArray, leftPtr, right - 1);
		    return leftPtr;
		  }
		  
		  public static int randInt(int min, int max) {
			    Random rand = new Random();
			    int randomNum = rand.nextInt((max - min) + 1) + min; // nextInt is normally exclusive of the top value, so add 1 to make it inclusive
			    return randomNum;
			}
}
