import java.util.Random;


public class ThreeSumZero {
	public static void main(String[] args) {
		int[] a = new int[25];
		
		for(int i = 0; i < a.length; i++) {
			a[i] = randInt(-25, 25);
			System.out.println("a[" + i + "]" + "  =  " + a[i]);
		}
		
		System.out.println("");
		System.out.println("");
		CheckIf3SumZeroExists(a, a.length, 0);
	}
	
	public static boolean CheckIf3SumZeroExists(int[] a, int arraySize, int sum) {
		// Fix the first element as A[i]
	    for (int i = 0; i < arraySize - 2; i++) {
	       // Fix the second element as A[j]
	       for (int j = i+1; j < arraySize - 1; j++) {
	           // Now look for the third number
	           for (int k = j+1; k < arraySize; k++) {
	               if (a[i] + a[j] + a[k] == sum) {
	            	 System.out.println("Triple that sums to 0: ");
	                 System.out.print("(" + a[i] + ", ");
	                 System.out.print(a[j] + ", ");
	                 System.out.print(a[k] + ")");
	                 return true;
	               }
	           }
	       }
	    }
	 // If we reach here, then no triplet was found
	    return false;
	}
	
	
	public static int randInt(int min, int max) {
	    Random rand = new Random();
	    int randomNum = rand.nextInt((max - min) + 1) + min; // nextInt is normally exclusive of the top value, so add 1 to make it inclusive
	    return randomNum;
	}
}
