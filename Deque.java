package hw4solutions;
import java.util.Iterator;

/**
 * Deque a linear data type allowing insertions and deletions at * both ends.
 *
 * @author (Terry Schmidt)
 * Class: CSC402
 */

public class Deque<E> implements Iterable<E> {
   private Node head;                                              // head node
   private Node tail;                                               // tail node
   private int sz;                                                // number of items in deque
    
   private class Node        {                                        // Structure
   
       private E data;                                           // data for each node
       private Node next;                                           // Reference to the next node
       private Node prev; 								// Reference to the previous node
   
   }
    
   public Deque() {                                                  // Construct an empty deque
       head = null;
       tail  = null;
       sz  = 0;
   }
    
   public boolean isEmpty() {   // Check if the deque is empty or not
	   if (sz == 0) {
		   return true;
	   }
	   else {
		   return false;
	   }
  }                  
    
   public int size() { 
	   return sz;      // Return the number of E's 
   }                   

   public void addFirst(E x) {                                  // Insert an E at the front
    
      if (x == null)
         throw new java.lang.NullPointerException();
      if (sz == 0) {
         head      = new Node();
         head.data = x;
         head.next = null;
         head.prev = null;
         tail = head;
      }
      else {
         Node oldhead = head;
         head         = new Node();
         head.data    = x;
         head.next    = oldhead;
         head.prev    = null;
         oldhead.prev = head;
      }
      sz++;
   }
    
   public void addLast(E x) {                                  // Insert an E at the end
   
      if (x == null)
         throw new java.lang.NullPointerException();
      if (sz == 0) {
         tail      = new Node();
         tail.data = x;
         tail.next = null;
         tail.prev = null;
         head     = tail;
      }
      else {
         Node oldtail = tail;
         tail         = new Node();
         tail.data    = x;
         tail.prev    = oldtail;
         oldtail.next = tail;
      }
      sz++;
   }

   public E removeFirst() {                                       // Delete and return the E at the front
   
      if (sz == 0)
         throw new java.util.NoSuchElementException();  // can't remoe the first node if there is no first node.
      else  {
         E e = head.data;
         Node n    = head;
         if (sz != 1) { 
            head      = head.next;
            head.prev = null; 
         }
         else {
            head = null;
            tail  = null;
         }
         sz--;
         n = null;
         return e;
      }
   }
    
   public E removeLast()  {                                       // Delete and return the E at the end
   
      if (sz == 0)
         throw new java.util.NoSuchElementException(); // can't remove the last node if there is no last node.
      else
      {
         E e = tail.data;
         Node n = tail;
         if (sz != 1) {
            tail      = tail.prev;
            tail.next = null;
         }
         else {
            head = null;
            tail  = null;
         }
         sz--;
         n = null;
         return e;          
      }
   }
    
   public Iterator<E> iterator() { 
	   return new DequeIterator(); } // Return an iterator over E's in order from front to end

   private class DequeIterator implements Iterator<E> {
      private Node current = head;

      public boolean hasNext() { 
    	  return current != null; 
    	  }
      
      public E next() {
          if (current == null)
             throw new java.util.NoSuchElementException(); // can't access next if there is no next.
          E e = current.data;
          current   = current.next;
          return e;
       }
       
      public void remove()     {  // lets the programmer know that remove is not a valid function, they must use removeFirst/removeLast
    	  throw new java.lang.UnsupportedOperationException();
    	  }
    
   }
}
