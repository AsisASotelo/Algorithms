/* *****************************************************************************
 *  Name: Asis Sotelo
 *  Date: 04/12/2021
 *  Description: Deqeue.java
 **************************************************************************** */

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private Node first; // Top of the deqeue.
    private Node last;  // Bottom of the deqeue.
    private int N; // Number of items.

    private class Node {
        Item item;
        Node next;
    }

    // Construct an empty Deque
    public Deque() {
        // Create using Nodes
        N = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return N == 0;
    }

    // return the number of items on the deque
    public int size() {
        return N;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        if (isEmpty()) {
            first.next = last;
        }
        else first.next = oldFirst;
        N++;


    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        Node oldLast = last;
        last = new Node();
        last.item = item;
        if (isEmpty()) {
            first.next = last;
        }
        else oldLast.next = last;
        N++;

    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (N == 0) {
            throw new NoSuchElementException();
        }
        Item item = first.item;
        first = first.next;
        N--;
        return item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (N == 0) {
            throw new NoSuchElementException();
        }
        Item item = last.item();

    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator()

    // unit testing (required)

    public static void main(String[] args) {

    }
}
