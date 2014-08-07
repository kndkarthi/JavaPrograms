package Hw2SolutionsPart2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.NoSuchElementException;
import java.util.Scanner;

import JustPractice.MyMinMaxPQ;
import algs13.Queue;
import stdlib.StdIn;
import stdlib.StdOut;

//Name: Terry Schmidt, Class: CSC403

public class MyBST<Key extends Comparable<Key>, Value> {
    private static final int INIT_CAPACITY = 2;
    private Key[] keys;
    private Value[] vals;
    private int N = 0;

    // create an empty symbol table with default initial capacity
    public MyBST() { 
    	this(INIT_CAPACITY);
    }   

    // create an empty symbol table with given initial capacity
    public MyBST(int capacity) { 
        keys = (Key[]) new Comparable[capacity]; 
        vals = (Value[]) new Object[capacity]; 
    }   

    // resize the underlying arrays
    private void resize(int capacity) {
        assert capacity >= N;
        Key[]   tempKeyArray = (Key[])   new Comparable[capacity];
        Value[] tempValArray = (Value[]) new Object[capacity];
        for (int i = 0; i < N; i++) {
            tempKeyArray[i] = keys[i];
            tempValArray[i] = vals[i];
        }
        vals = tempValArray;
        keys = tempKeyArray;
    }


    // is the key in the table?
    public boolean contains(Key key) {
        return get(key) != null;
    }

    // number of key-value pairs in the table
    public int size() {
        return N;
    }

    // is the symbol table empty?
    public boolean isEmpty() {
        if(size() == 0) {
        	return true;
        } else {
        	return false;
        }
    }

    // return the value associated with the given key, or null if no such key
    public Value get(Key key) {
        if (isEmpty()) return null;
        int i = rank(key); 
        if (i < N && keys[i].compareTo(key) == 0) return vals[i];
        return null;
    } 


    // Search for key. Update value if found; grow table if new. 
    public void put(Key key, Value val) {
        if (val == null) { 
        	delete(key); 
        	return;
        }

        int i = rank(key);
        
        //modification for 3.1.28 of HW1 below - inserting a key that is larger than all keys in the table takes constant time
        if(rank(key) == N) {
        	if (N == keys.length) {
        		resize(2*keys.length);
        	}
        	
            keys[N] = key;
            vals[N] = val;
            N++;
        }

        // key is already in table
        if (i < N && keys[i].compareTo(key) == 0) {
            vals[i] = val;
            return;
        }

        // insert new key-value pair
        if (N == keys.length) resize(2*keys.length);

        for (int j = N; j > i; j--)  {
            keys[j] = keys[j-1];
            vals[j] = vals[j-1];
        }
        
        keys[i] = key;
        vals[i] = val;
        N++;

        assert verify();
    } 


    // Remove the key-value pair
    public void delete(Key key)  {
        if (isEmpty()) return;

        // compute rank
        int i = rank(key);

        // key not in table
        if (i == N || keys[i].compareTo(key) != 0) {
            return;
        }

        for (int j = i; j < N-1; j++)  {
            keys[j] = keys[j+1];
            vals[j] = vals[j+1];
        }

        N--;
        keys[N] = null;
        vals[N] = null;

        // resize if 1/4 full
        if (N > 0 && N == keys.length/4) resize(keys.length/2);

        assert verify();
    } 

    // delete the minimum key and its associated value
    public void deleteMin() {
        if (isEmpty()) throw new NoSuchElementException("No such element");
        delete(min());
    }

    // delete the maximum key and its associated value
    public void deleteMax() {
        if (isEmpty()) throw new NoSuchElementException("No such element");
        delete(max());
    }
    
    // return the number of keys in the table that are smaller than given key
    // method for HW2 - 3.2.14
    public int rank(Key key) {
        int low = 0, high = N-1; 
        while (low <= high) { 
            int x = low + (high - low) / 2; 
            int compare = key.compareTo(keys[x]); 
            if      (compare < 0) high = x - 1; 
            else if (compare > 0) low = x + 1; 
            else return x; 
        } 
        return low;
    } 

    //method for HW2 - 3.2.14
    public Key min() {
        if (isEmpty()) return null;
        return keys[0]; 
    }
    //method for HW2 - 3.2.14
    public Key max() {
        if (isEmpty()) return null;
        return keys[N-1];
    }
    //method for HW2 - 3.2.14
    public Key select(int y) {
        if (y < 0 || y >= N) return null;
        return keys[y];
    }

    //method for HW2 - 3.2.14
    public Key floor(Key key) {
        int i = rank(key);
        if (i < N && key.compareTo(keys[i]) == 0) return keys[i];
        if (i == 0) return null;
        else return keys[i - 1];
    }
    //method for HW2 - 3.2.14
    public Key ceiling(Key key) {
        int i = rank(key);
        if (i == N) return null; 
        else return keys[i];
    }

    public int size(Key low, Key high) {
        if (low.compareTo(high) > 0) return 0;
        if (contains(high)) return rank(high) - rank(low) + 1;
        else  return rank(high) - rank(low);
    }

    public Iterable<Key> keys() {
        return keys(min(), max());
    }

    public Iterable<Key> keys(Key low, Key high) {
        Queue<Key> queue = new Queue<Key>(); 
        if (low == null && high == null) return queue;
        if (low == null) throw new NullPointerException("low is null in keys()");
        if (high == null) throw new NullPointerException("high is null in keys()");
        if (low.compareTo(high) > 0) return queue;
        for (int i = rank(low); i < rank(high); i++) 
            queue.enqueue(keys[i]);
        if (contains(high)) queue.enqueue(keys[rank(high)]);
        return queue; 
    }

    private boolean verify() {
        return isSorted() && checkRank();
    }

    // are the items in the array in ascending order?
    private boolean isSorted() {
        for (int j = 1; j < size(); j++)
            if (keys[j].compareTo(keys[j-1]) < 0) return false;
        return true;
    }

    // check that rank(select(i)) = i
    private boolean checkRank() {
        for (int i = 0; i < size(); i++)
            if (i != rank(select(i))) return false;
        for (int i = 0; i < size(); i++)
            if (keys[i].compareTo(select(rank(keys[i]))) != 0) return false;
        return true;
    }

    //to use this main to test the rest of the file, simply provide with the path of a .txt file with strings in it. 
    //It will box the strings with a number (0, 1, 2 ... n) and place it on the symbol table.
    //I used:
    //C:\Users\terry\Desktop\DePaul Classes\Data Structures II\infile.txt
    //infile.txt contents below:
    
    //Hello
    //Java
    //String
    //Integer
    
    //I included this file in my .zip submission for you to use
    public static void main(String[] args) {
		StdOut.print("file: "); //prompt for file
		String filePath = StdIn.readLine(); // read the path given

		final File in = new File(filePath);
		try {

			if (!in.exists()) { //if we're given an incorrect path
				System.out.println("Unable to open the text source " + filePath); // print this
				System.exit(1); // exit
			}
			
		Scanner inputStream = new Scanner(in);

		MyBST<String, Integer> st = new MyBST<String, Integer>();
		int i = 0;
		System.out.println("Strings to be inserted on the ST below:");
        while (inputStream.hasNext()) {
            String item = inputStream.next();
            System.out.println(item);
            
            	st.put(item,i);
            	i++;
        }
        
        System.out.println("");
        System.out.println("Order of strings after insertion: ");
        for (String s: st.keys())
            StdOut.println(s + " " + st.get(s));
        
        System.out.println("");
        System.out.println("Printing the minimum: ");
        System.out.println(st.min());
        
        System.out.println("");
        System.out.println("Printing the maximum: ");
        System.out.println(st.max());
        
        System.out.println("");
        System.out.println("Printing floor of 'Real': " + st.floor("Real"));
        
        System.out.println("");
        System.out.println("Printing ceiling of 'Real': " + st.ceiling("Real"));
        
        System.out.println("");
        System.out.println("Printing rank of 'Java': " + st.rank("Java"));
        
        System.out.println("");
        System.out.println("Printing select of '2': " + st.select(2));
        
    } catch (FileNotFoundException jhgk) {}
	}
}
