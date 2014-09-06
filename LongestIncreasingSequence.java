package WarmUp;

/**
 * Created by terry on 9/4/14.
 */

public class LongestIncreasingSequence {
        /*
        Write a program that is given an array of integers as input and that outputs the length of the longest increasing sequence. A sequence is a series of
        adjacent elements. For example, in the array [21, 23, 28, 23, 29, 32, 38, 28, 30, 29, 25, 24] the length of the longest increasing sequence is 4. The
        sequence starts at position 3 (the second occurrence of 23) and ends at position 6 (the value 38). The program should work for any length array.
         */

    public static void main(String[] args) {
            int[] a;
            a = new int[] {21, 23, 28, 23, 29, 32, 38, 28, 30, 29, 25, 24};
        System.out.println(FindLongest(a));
    }

    public static int FindLongest(int[] a) {
        int n = a.length - 1;
        int currentIncreasingSequenceCount = 0;
        int longestIncreasingSequenceCount = 0;

        for(int i = 0; i < n; i++) {
            if(a[i] < a[i + 1]) {
                currentIncreasingSequenceCount++;
            }

            if(a[i] >= a[i + 1]) {
               if(currentIncreasingSequenceCount > longestIncreasingSequenceCount) {
                    longestIncreasingSequenceCount = currentIncreasingSequenceCount;
               }
            currentIncreasingSequenceCount = 0;
            }

        }

        return longestIncreasingSequenceCount + 1;
    }
}
