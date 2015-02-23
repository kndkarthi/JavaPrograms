
public class shiftZeros {
	public static void main(String[] args) {
		int[] test = {10, 7, 0, 5, 10000, 0, 9, 0, 100, 0, 33, 66, 99, 0, 1, 2, 3};
		shift(test);
		
		for(int i = 0; i < test.length; i++) {
			System.out.println(test[i]);
		}
	}
	
	
	
	public static void shift(int[] a) {
	    for(int i = 0; i < a.length-1; i++) {
	        if(a[i] == 0) {
	            for(int j = i; j < a.length-1; j++) {
	                a[j] = a[j+1];
	            }
	            a[a.length-1] = 0;
	        }
	    }
	}
}
