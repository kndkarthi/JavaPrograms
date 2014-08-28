package WarmUp;

import java.util.Arrays;

public class RecursiveSelectionSort {
	
	/*
	 
	 SelectionSort(array, first)
	  if first == array length then
	    return
	  end if
	  set minIndex to first
	  for i = first to array length - 1 do
	    if array[i] < array[minIndex] then
	      set minIndex to i
	    end if
	  end for
	  swap array[first] and array[minIndex]
	  SelectionSort(array, first+1)
	end SelectionSort
	
	*/
	
	public static void main(String args[]) {
		int[] a = {40,30,20,10};
		goSort(a);
		System.out.println("");
		int[] b = {10,20,30,40};
		goSort(b);
		int[] c = {1, 20, 78, 94, 3, 22, 17};
		goSort(c);
	}
	
	public static void swap(int array[], int i, int j) {
		int b;
		b = array[i];
		
		array[i] = array[j];
		array[j] = b;
		System.out.format("%-20s %-10s%-10s\n",Arrays.toString(array),"","SWAPPED("+array[i]+","+array[j]+")");
	}
	
	public static void goSort(int array[]) {
		int first=0;
		System.out.format("%-20s %-10s%-10s\n",Arrays.toString(array),first,"START");
		
		sort(array,first);
		System.out.format("%-20s %-10s%-10s\n",Arrays.toString(array),first,"END");
	}
	
	public static void sort(int[] array, int first) {
		System.out.format("%-20s %-10s%-10s\n",Arrays.toString(array),first,"TOP");
		if(first == array.length) {
			return;
		}
		
		int minIndex = first;
		for(int i = first; i < array.length; i++) {
			if(array[i] < array[minIndex]) {
				minIndex = i;
			}
			
			System.out.format("%-20s %-10s%-10s%-10s%-10s%-10s%-10s\n",Arrays.toString(array),first,"    FOR",i,minIndex,array[i],array[minIndex]);
		}
		swap(array, first,minIndex);
		sort(array,first+1);
		
		System.out.format("%-20s %-10s%-10s\n",Arrays.toString(array),first,"BOT");
	}
}
