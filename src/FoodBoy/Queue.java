package FoodBoy;

import java.util.NoSuchElementException;

public class Queue<E> {
	
	private Node<E> head, tail;
	private int numElements;
	
	public Queue() {
		numElements = 0;
	}
	
	public void enqueue( E item ) {
		if (tail != null) {
			Node<E> hold = new Node<E> (item);
			tail.setNext(hold);
			tail = hold;
		}
		else{
			tail = new Node<E> (item);
		}
			
		if (head == null)
			head = tail;
		
		numElements++;
	}
	
	public E dequeue() throws NoSuchElementException {
		
		if (numElements == 0)
			throw new NoSuchElementException();
		
		Node<E> hold = head;
		head = head.getNext();
		numElements--;
		return hold.getValue();
	}
	
	public E peek() throws NoSuchElementException {
		if (numElements == 0)
			throw new NoSuchElementException();
		return head.getValue();
	}
	
	public boolean isEmpty() {
		if (numElements == 0)
			return true;
		return false;
	}
	
	public int size() { 
		return numElements;
	}

	public int search( E item ) { 
		
		int count = 0;
		Node<E> hold = head;
		//Queue187<E> hold = new Queue187<E>(); 
		
		if (head == null)
			return -1;
		if (head.getValue().equals(item))
			return 0;
		
		//if (!this.isEmpty()) {
			head = head.getNext();
			count = 1 + this.search(item);
			
			if (count == 0)
				count = -1;
		//}
		
		head = hold;
		
		return count;
	}

}
