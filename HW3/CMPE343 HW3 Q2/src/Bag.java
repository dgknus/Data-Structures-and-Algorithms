//-----------------------------------------------------
// Title: Bag class
// Author: Do�ukan Us
// ID: 47113751436
// Section: 03
// Assignment: 2
// Description: This class is to define the structure of Bag object inspired from textbook
//-----------------------------------------------------

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Bag<Item> implements Iterable<Item> {
	
    private Node<Item> first;    // First node in bag
    private int n;               // number of elements in bag

    // helper linked list class
    private static class Node<Item> {
        private Item item;
        private Node<Item> next;
    }

    //Constructor which creates empty bag
    public Bag() {
        first = null;
        n = 0;
    }

    
    //Returns true if this bag is empty.
    public boolean isEmpty() {
        return first == null;
    }

    //Returns the number of items in this bag.
    public int size() {
        return n;
    }

    
    //Adds the item to this bag.
    public void add(Item item) {
        Node<Item> oldfirst = first;
        first = new Node<Item>();
        first.item = item;
        first.next = oldfirst;
        n++;
    }


    //Returns an iterator that iterates over the items in this bag in arbitrary order.
    public Iterator<Item> iterator()  {
        return new LinkedIterator(first);  
    }

    // an iterator which doesn't implement remove() since it's optional
    private class LinkedIterator implements Iterator<Item> {
        private Node<Item> current;

        public LinkedIterator(Node<Item> first) {
            current = first;
        }

        public boolean hasNext()  { return current != null;                     }
        public void remove()      { }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next; 
            return item;
        }
    }
}