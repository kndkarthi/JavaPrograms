package WarmUp;

public class LCSwithMatrix {
	
    static int firstStringLength = 0;
    static int secondStringLength = 0;
    static int[][] stringMatrix;
    static int counter = 0;
    
	    public static void main(String[] args) {
	    	System.out.print("Please enter the first string: ");
	        String firstString = StdIn.readString();
	        System.out.print("Please enter the second string: ");
	        String secondString = StdIn.readString();
	        firstStringLength = firstString.length();
	        secondStringLength = secondString.length();
	        System.out.println("");
	        System.out.println("The LCS Matrices are:  ");

	       stringMatrix = new int[firstStringLength + 1][secondStringLength + 1];
	       // [0][0] [0][1] [0][2] ... [0][length-1]
	       // [0][0] [0][1] [0][2] ... [1][length-1]
	       // [0][0] [0][1] [0][2] ... [2][length-1]

	       String LCSaccumulator = "";
	       
	        		// Figure out the LCS and its length
	        		for (int i = firstStringLength - 1; i >= 0; i--) {
	        			for (int j = secondStringLength - 1; j >= 0; j--) {
	        				if (firstString.charAt(i) == secondString.charAt(j)) {
	        					stringMatrix[i][j] = stringMatrix[i + 1][j + 1] + 1;
	        					System.out.print("\nMatch: "+ firstString.charAt(i));
	        					LCSaccumulator = firstString.charAt(i) + LCSaccumulator;
	        					counter++;
	        			     } 
	                
	        				else {
	        					System.out.format("\nNo-Match: %s!=%s",firstString.charAt(i),secondString.charAt(j));	                	
	        					stringMatrix[i][j] = Math.max(stringMatrix[i + 1][j], stringMatrix[i][j + 1]);	                	
	        				}
	                
	        			System.out.format("\n(%d,%d) ", i, j);
	        			printMatrix();
	        			System.out.println("");
	        		   }
	        	    }
	        
	        System.out.println("");
	        System.out.println("The length of the LCS is: " + counter);
	        System.out.print("The LCS is: " + LCSaccumulator);
	    }
	    
	    
	    	public static void printMatrix() {
	    				System.out.println("");
	    				System.out.print("    ");
	    		for (int i = 0; i <firstStringLength + 1; i++) {
	    				System.out.format("%3s",i);
	    		}
	    				System.out.println("");
	    				System.out.print("    ");
	    		for (int i = 0; i <firstStringLength + 1; i++) {
	    				System.out.format("%3s","--");
	    		}
	    				System.out.println("");

	    		for (int i = 0; i <firstStringLength + 1; i++) {
	    				System.out.format("%2d| ",i);	            
	    				for (int j = 0; j <secondStringLength + 1; j++) {
	    					System.out.format("%3d",stringMatrix[i][j]);
	    				}
	            		System.out.println();
	    		}
	    	}
}
