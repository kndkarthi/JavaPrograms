package hw5solutions;
import java.util.Iterator;

import java.util.*;

//name: Terry Schmidt, ID#: 1433009, CSC402

public class MyMinPQ<E extends Comparable<E>> implements Iterable<E> {
	private int sz           ;
	private int myCursor     ;
	private ArrayList<E> list;
	
	public MyMinPQ() {
		list = new ArrayList<E>();
		list.add(null);
		sz = 0;
		myCursor = 0;
	}
	
	public boolean isEmpty() {
		if (sz == 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public void insert(E item) {
		int k = sz;
		list.add(item);
		sz++;
	  	while(k <= sz - 1 && k > 0) {
	  	if(((Comparable<E>)list.get(k)).compareTo(list.get(k+1)) < 0) {
	  		k--;
	  		} else {
	  			if(((Comparable<E>)list.get(k)).compareTo(list.get(k+1)) == 0){
	  				break;
	  			}
	  			else {
	  			E tmp = list.get(k);
	  			list.set(k, list.get(k+1));
	  			list.set(k+1, tmp);
	  		}
	  	}
	  	}
	}
	
	public int size() {
		return sz;
	}
	
	public E delMin() {
		int k;
		E min;
		if(sz == 0) {
			throw new java.util.NoSuchElementException();
		}
		else {
			min = list.get(1);
			for(k = 1; k < sz; k++) {
				list.set(k, list.get(k+1));
			}
			sz-- ;
			return min;
		}
	}
	
	public E min() {
		E min;
		if(!isEmpty()) {
			min = list.get(1);
			return min;
		}
		return null;
	}
	
	
	 public Iterator<E> iterator() { 
		 return new MyMinPQIterator();
	 }
	 
	 private class MyMinPQIterator implements Iterator<E> {
		 
		public boolean hasNext() {
	    	  return (sz - myCursor > 0) ;
	     }
		 
		 public E next() {
			 myCursor++ ;
			 return list.get(myCursor);
		 }

		@Override
		public void remove() {
			// TODO Auto-generated method stub
			
		}
	 }
	
}

