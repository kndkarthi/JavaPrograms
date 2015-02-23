import java.util.Scanner;

public class reverseString {
	public static void main(String[] args) {
		String original = "";
		String reversed = "";
		
		Scanner in = new Scanner(System.in);
		
		original = in.nextLine();
		
		for(int i = original.length() - 1; i >= 0; i--) {
			reversed = reversed + original.charAt(i);
		}
		
		System.out.println(reversed);
	}
}
