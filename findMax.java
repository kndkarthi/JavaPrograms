package WarmUp;

import java.util.*;

public class findMax {
	/*
	 FindMax(array, first, last)
  if first == last then
    return array[first]
  end if
  set middle to (first + last) / 2
  set max1 to FindMax(array, first, middle)
  set max2 to FindMax(array, middle+1, last)
  return the larger of max1 and max2
end FindMax
	 */
	
	public static void main(String[] args) {
		int[] nums = { 1, 2, 3, 4, 9, 6, 22};
		int first = 0;
		int last = nums.length - 1;
		System.out.println(findMax(nums, first, last));
	}
	
	public static int findMax(int[] nums, int first, int last) {
		if(first == last) {
			return nums[first];
		}
		
		int middle = (first + last) / 2;
		int max1 = findMax(nums, first, middle);
		int max2 = findMax(nums, middle + 1, last);
		
		if(max1 > max2) {
			return max1;
		} else {
			return max2;
		}
	}
}
