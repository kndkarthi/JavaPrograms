package WarmUp;

// Terry Schmidt, October 2014

public class LongestCommonSubsequence {
	
	static String firstString;
	static String secondString;
    static int firstStringLength;
    static int secondStringLength;
    static int[][] stringMatrix;
    
	    public static void main(String[] args) {
	    	System.out.print("Please enter the first string: ");
	        firstString = StdIn.readString();
	        System.out.print("Please enter the second string: ");
	        secondString = StdIn.readString();
	        firstStringLength = firstString.length();
	        secondStringLength = secondString.length();
	        System.out.println("");

	       stringMatrix = new int[firstStringLength + 1][secondStringLength + 1];
	       // [0][0] [0][1] [0][2] ... [0][length-1]
	       // [0][0] [0][1] [0][2] ... [1][length-1]
	       // [0][0] [0][1] [0][2] ... [2][length-1]
	       
	        		// Figure out the LCS
	        		for (int i = firstStringLength - 1; i >= 0; i--) {
	        			for (int k = secondStringLength - 1; k >= 0; k--) {
	        				if (firstString.charAt(i) == secondString.charAt(k)) {
	        					stringMatrix[i][k] = stringMatrix[i + 1][k + 1] + 1;
	        			     } 
	                
	        				else {               	
	        					stringMatrix[i][k] = Math.max(stringMatrix[i + 1][k], stringMatrix[i][k + 1]);	                	
	        				}
	        		   }
	        	    }
	        printLCSandLCSsize();
	    }
	    
	    	
	    	public static void printLCSandLCSsize() {
	    		int y = 0; int z = 0; int count = 0;
	    		System.out.print("The LCS is: ");
	    		
	    		//find and print LCS
	    		while(y < firstStringLength && z < secondStringLength) {
	    			if(firstString.charAt(y) == secondString.charAt(z)) {
	    				System.out.print(firstString.charAt(y));
	    				y++; z++; count++;
	    			}
	    			else if (stringMatrix[y+1][z] >= stringMatrix[y][z+1]) y++;
	    			else {
	    				z++;
	    			}
	    		}
	    		System.out.println("");
	    		System.out.println("The LCS size is : " + count);
	    	}
}
