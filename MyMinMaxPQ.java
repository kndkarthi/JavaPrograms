package JustPractice;

import java.util.NoSuchElementException;
import java.util.Scanner;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.OutputStreamWriter;
import stdlib.StdIn;
import stdlib.StdOut;

//Name: Terry Schmidt, Class: CSC403

public class MyMinMaxPQ<T extends Comparable<T>> {
	private T[] maxHeap; // generic array to hold the data in a way that represents a max heap
	private T[] minHeap; // generic array to hold the data in a way that represents a min heap
	private Integer[] maxMin;  //int array for keeping track of correspondence
	private Integer[] minMax; //int array for keeping track of correspondence
	private int size; //int variable for keeping track of the size

	public MyMinMaxPQ() {
		maxHeap = (T[])new Comparable[20]; //initialize array
		minHeap = (T[])new Comparable[20]; //initialize array
		maxMin = new Integer[20]; //initialize array
		minMax = new Integer[20]; //initialize array
		size = 0; //initialize size
	}

	public void insert(T item) {
		
		if(size >= maxHeap.length - 1)
			resize(2 * maxHeap.length);

		maxHeap[++size] = item;
		minHeap[size] = item;

		maxMin[size] = size;
		minMax[size] = size;

		maxSwim(size);
		minSwim(size);
	}

	public T deleteMaximum() {
		if(isEmpty())
			throw new NoSuchElementException();

		T max = maxHeap[1];
		maxExchange(1,size);
		minExchange(minMax[size],size);

		size--;

		if(size > 0) {
			maxSink(1);
			minSink(minMax[size]);

			maxHeap[size + 1] = null;
			minHeap[size + 1] = null;

			if(size <= (maxHeap.length - 1) / 4)
				resize(maxHeap.length / 2);
		}
		return max;
	}

	public T deleteMinimum() {
		if(isEmpty())
			throw new NoSuchElementException();

		T min = minHeap[1];
		minExchange(1,size);
		maxExchange(maxMin[size],size);

		size--;

		if(size > 0) {
			minSink(1);
			maxSink(maxMin[size]);

			minHeap[size + 1] = null;
			maxHeap[size  + 1] = null;

			if(size <= (minHeap.length - 1) / 4)
				resize(minHeap.length / 2);
		}
		return min;
	}
	
	public boolean isEmpty() {
		if(size <= 0) {
			return true;
		} else {
			return false;
		}
	}

	public int size() {
		return size;
	}

	public T findMaximum() {
		if(isEmpty())
			throw new NoSuchElementException();
		return maxHeap[1];
	}

	public T findMinimum() {
		if(isEmpty())
			throw new NoSuchElementException();
		return minHeap[1];
	}

	private void maxSink(int indx) {
		while(indx <= size / 2) {
			int child = 2 * indx;
			if(child < size && maxHeap[child].compareTo(maxHeap[child  + 1]) < 0)
				child++;
			if(maxHeap[indx].compareTo(maxHeap[child]) > 0)
				break;
			maxExchange(indx,child);
			indx = child;
		}
	}

	private void minSink(int indx) {
		while(indx <= size / 2) {
			int child = 2 * indx;
			if(child < size && minHeap[child].compareTo(minHeap[child + 1]) > 0)
				child++;
			if(minHeap[indx].compareTo(minHeap[child]) < 0)
				break;
			minExchange(indx,child);
			indx = child;
		}
	}
	
	private void maxSwim(int index) {
		while(index > 1 && maxHeap[index / 2].compareTo(maxHeap[index]) < 0) {
			maxExchange(index / 2,index);
			index /= 2;
		}
	}

	private void minSwim(int index) {
		while(index > 1 && minHeap[index / 2].compareTo(minHeap[index]) > 0) {
			minExchange(index / 2,index);
			index /= 2;
		}
	}

	private void resize(int newSize) {
		T[] newMinHeap = (T[])new Comparable[newSize];
		for(int i = 0; i <= size; i++)
			newMinHeap[i] = minHeap[i];
		minHeap = newMinHeap;

		T[] newMaxHeap=(T[])new Comparable[newSize];
		for(int i = 0; i <= size; i++)
			newMaxHeap[i] = maxHeap[i];
		maxHeap = newMaxHeap;

		Integer[] newMaxToMin=new Integer[newSize];
		for(int i = 0; i <= size; i++)
			newMaxToMin[i] = maxMin[i];
		maxMin = newMaxToMin;

		Integer[] newMinToMax=new Integer[newSize];
		for(int i = 0; i <= size; i++)
			newMinToMax[i] = minMax[i];
		minMax = newMinToMax;
	}
	
	private void minExchange(int position1,int position2) {
		T temp = minHeap[position1];
		minHeap[position1] = minHeap[position2];
		minHeap[position2] = temp;

		Integer tempPos = maxMin[position1];
		maxMin[position1] = maxMin[position2];
		maxMin[position2] = tempPos;

		tempPos = minMax[maxMin[position1]];
		minMax[maxMin[position1]] = minMax[maxMin[position2]];
		minMax[maxMin[position2]] = tempPos;
	}

	private void maxExchange(int position1, int position2) {
		T temp = maxHeap[position1];
		maxHeap[position1] = maxHeap[position2];
		maxHeap[position2] = temp;

		Integer tempPos = minMax[position1];
		minMax[position1] = minMax[position2];
		minMax[position2] = tempPos;

		tempPos = maxMin[minMax[position1]];
		maxMin[minMax[position1]] = maxMin[minMax[position2]];
		maxMin[minMax[position2]] = tempPos;
	}

	public static void main(String[] args) {
		PrintWriter output = new PrintWriter(new OutputStreamWriter(System.out),true);
		
		StdOut.print("file: "); //prompt for file
		String filePath = StdIn.readLine(); // read the path given

		final File in = new File(filePath);
		try {

			if (!in.exists()) { //if we're given an incorrect path
				System.out.println("Unable to open the text source " + filePath); // print this
				System.exit(1); //exit
			}
			
		Scanner inputStream = new Scanner(in);

        MyMinMaxPQ<String> pq = new MyMinMaxPQ<String>();
        while (inputStream.hasNext()) {
            String item = inputStream.next();
            System.out.println(item);
            if (!(item.equals("-") || item.equals("+"))) {
            	pq.insert(item);
            }
            else if (!pq.isEmpty() && item.equals("+")) {
            	output.print(pq.deleteMaximum() + " ");
            }
			else if (!pq.isEmpty() && item.equals("-")) {
				output.print(pq.deleteMinimum() + " ");
			}
        }
        
        System.out.println(pq.size() + " item(s) left on the MinMaxPriorityQueue.");
        
    } catch (FileNotFoundException jhgk) { }
	}
}
