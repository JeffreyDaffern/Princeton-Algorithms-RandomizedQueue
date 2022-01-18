package a02;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Implements a deque that is a doubly linked list. A deque can access previous
 * and next nodes, allowing removal of items from the front and back of the data
 * structure.
 * 
 * @author Jeff daffern and Tommy Xa
 *
 * @param <Item>
 */
class Deque<Item> implements Iterable<Item>
{
	private int size;
	private Node first, last;

	private class Node
	{
		private Item item;
		private Node previous;
		private Node next;
	}

	/**
	 * Creates an empty deque.
	 */
	public Deque()
	{
		size = 0;
		first = null;
		last = null;
	}

	/**
	 * Determines whether the deque is empty.
	 * 
	 * @return true if empty, false otherwise
	 */
	public boolean isEmpty()
	{
		return size == 0;
	} // is the deque empty?

	/**
	 * Gives the number of items in the deque.
	 * 
	 * @return the size of the deque
	 */
	public int size()
	{
		return size;
	}

	/**
	 * Inserts an item at the front of the deque.
	 * 
	 * @param item the item to be added
	 */
	public void addFirst(Item item)
	{
		if (item == null)
		{
			throw new NullPointerException();
		}

		Node newNode = first;
		first = new Node();
		first.item = item;
		first.previous = null;
		first.next = newNode;

		if (newNode != null)
			newNode.previous = first;
		else
			last = first;

		size++;
	}

	/**
	 * Inserts an item at the end of the deque.
	 * 
	 * @param Item the item to be added
	 */
	public void addLast(Item item)
	{
		if (item == null)
			throw new IllegalArgumentException();

		Node newNode = last;
		last = new Node();
		last.item = item;
		last.next = null;
		last.previous = newNode;

		if (newNode != null)
			newNode.next = last;
		else
			last = first;

		size++;
	}

	/**
	 * Deletes and returns the item at the front of the deque.
	 * 
	 * @return the item that was deleted
	 */
	public Item removeFirst()
	{
		if (isEmpty())
			throw new NoSuchElementException();

		Node newNode = null;

		if (first != null)
		{
			newNode = first;

			if (first.next != null)
				first = first.next;

			first.previous = null;
		} else
			last = null;

		size--;
		return newNode.item;
	}

	/**
	 * Deletes and returns the item at the end of the deque.
	 * 
	 * @return the item that was deleted
	 */
	public Item removeLast()
	{
		if (isEmpty())
			throw new NoSuchElementException();

		Node newNode = null;
		last = last.previous;

		if (last != null)
		{
			newNode = last;
			last.next = null;
		} else
			first = null;

		size--;
		return newNode.item;
	}

	/**
	 * Creates an iterator that iterates over items in order from front to end.
	 */
	public Iterator<Item> iterator()
	{
		return new DequeIterator();
	}

	private class DequeIterator implements Iterator<Item>
	{
		private Node current = first;
		private Item tempItem;

		public boolean hasNext()
		{
			return current != null;
		}

		public void remove()
		{
			throw new UnsupportedOperationException();
		}

		public Item next()
		{
			if (!hasNext())
				throw new NoSuchElementException();
			else
			{
				tempItem = current.item;
				current = current.next;
				return tempItem;
			}
		}
	}

	public static void main(String[] args)
	{
	} // unit testing
}
