package WarmUp;

// Terry Schmidt, October 2014

public class StringCutting {
	static String stringToBeCut = "abcdefghijklmnopqrst";  //length 20
	
		public static void main(String[] args) {
			
			System.out.println("String to be cut: " + stringToBeCut);
			System.out.println("");
			System.out.println("Best order to perform cuts:");
			
			//0 should always be the first element, the last element should always be the length of the string to be split
			int breakPoints[] = {0, 2, 8, 10, stringToBeCut.length()};
			getMinCutCosts(breakPoints);
		}
		
		
		
	    public static int getMinCutCosts(int[] breakPointArray) {
	        if (breakPointArray.length == 0) {
	        	System.out.println("You need to pass in an array with a length greater than 0.");
	            return -1;
	        }
	         
	        int len = breakPointArray.length;
	        int[][] arr = new int[len][len];
	        for (int zx = 2; zx < len; zx++) {
	            for (int i = 0; i < len - zx; i++) {
	                int j = i + zx;
	                int min = Integer.MAX_VALUE;
	                for (int k = i + 1; k < j; k++) {
	                    int tmp = arr[i][k] + arr[k][j] + breakPointArray[j] - breakPointArray[i];
	                    if (tmp < min)
	                        min = tmp;
	                }
	                 
	                arr[i][j] = min;
	            }
	        }
	         
	        printMinCutCosts(arr, 0, len - 1, breakPointArray);
	         
	        return arr[0][len - 1];
	    }
	    
	    
	    
	    public static void printMinCutCosts(int[][] arr, int i, int j, int[] a) {
	        if (i + 1 >= j) {
	            return;
	        }
	         
	        for (int k = i + 1; k < j; k++) {
	            int tmp = arr[i][k] + arr[k][j] + a[j] - a[i];
	            if (tmp == arr[i][j]) {
	            	System.out.println("");
	                System.out.print("Cut after char " + a[k] + ": ");
	                for (int z = 0; z < a[k]; z++) {
	                	System.out.print(stringToBeCut.charAt(z));
	                }
	                printMinCutCosts(arr, i, k, a);
	                printMinCutCosts(arr, k, j, a);
	                
	                return;
	            }
	        }
	    }
	}
