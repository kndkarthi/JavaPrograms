import java.io.*;
import java.util.Arrays;
import java.util.Scanner;


public class Anagrams {
   public static void main (String[] args) {
      String string1;
      String string2;
 
      Scanner in = new Scanner(System.in);
      string1 = in.nextLine();
      string2 = in.nextLine();
      
       areAnagrams(string1, string2);
   }
    
    public static boolean areAnagrams(String s1, String s2) {
    char[] ch1 = s1.toCharArray();
    char[] ch2 = s2.toCharArray();
    Arrays.sort(ch1);
    Arrays.sort(ch2);
        
    if(Arrays.equals(ch1, ch2)) {
        System.out.println("Anagrams!");
    } else {
        System.out.println("Not anagrams!");
    }
        
    return Arrays.equals(ch1,ch2);
    }
}
