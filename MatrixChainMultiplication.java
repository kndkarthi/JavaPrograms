package finalCSC421;

//Terry Schmidt, November 2014
//This program does not actually do matrix multiplication, it figures out the best order in which to multiply some matrices in order to lessen the amount of total operations.

public class MatrixChainMultiplication {
	//This is the array being read in from file.  It signifies a series of matrices in this way:
	//There are 6 matrices, and the array signifies the following sizes of each:
	//30x35, 35x15, 15x5, 5x10, 10x20, 20x25
	
	//public static int[] p = {10, 100, 5, 50};  //this example should return 7500, the optimal amount of multiplication operations for these matrices
	public static int[] p = {30, 35, 15, 5, 10, 20, 25};  //should return 15125
	protected static int m[][];
	protected static int s[][];
	protected static int n;

	public static void main(String[] args) {
		
		System.out.println("The array of matrices is: ");
		System.out.print("{");
		for(int i = 0; i < p.length; i++) {
			System.out.print(p[i] + " ");
		}
		System.out.print("}");
		System.out.println("");
		System.out.println("");
		
		System.out.println("The optimal parenthesization for those matrices costs: " + matrixChainOrder());
		System.out.println();
		printOptimalParens(s, 0, n - 1);
	}

	//pseudocode on page 375 of Introduction to Algorithms (3rd edition)
	public static int matrixChainOrder() {
		n = p.length - 1;
		m = new int[n][n];
		s = new int[n][n];
		for (int i = 0; i < n; i++) {
			m[i][i] = 0;
		}
		
		for (int row = 1; row < n; row++) {
			for (int i = 0; i < n - row; i++) { // values per row
				int j = i + row;
				m[i][j] = Integer.MAX_VALUE; // set to infinity
				for (int k = i; k < j; k++) {
					 int q = m[i][k] + m[k + 1][j] + p[i]* p[k + 1] * p[j + 1];
						if (k == i) {
							m[i][j] = q;
							s[i][j] = k;
						} 	else if (k == i + 1) {
						if (m[i][j] > q) {
							m[i][j] = q;
							s[i][j] = k;
						}
						} else {
							if (q < m[i][j]) {
								m[i][j] = q;
								s[i][j] = k;
							}
						}
				}
				
				if (row == p.length - 2) {  //check to see we're at the right spot
					return m[i][j];  // return cost
				}
			}
		}
		return -1;  //error
	}

	//pseudocode on page 377 of Introduction to Algorithms (3rd edition)
	static void printOptimalParens(int[][] s, int i, int j) {
		if (i == j) {
			System.out.print((i + 1));
		} else {
			System.out.print("(");
			printOptimalParens(s, i, s[i][j]);
			printOptimalParens(s, s[i][j] + 1, j);
			System.out.print(")");
		}
	}
}
