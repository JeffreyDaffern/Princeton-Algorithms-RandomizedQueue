package a02;

import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.*;

/**
 * Implements a custom data structure that is similar to a queue, but returns
 * items at random.
 * 
 * @author Jeff Daffern and Tommy Xa.
 *
 * @param <Item>
 */
class RandomizedQueue<Item> implements Iterable<Item>
{
	private Item[] items;
	private int size = 0, randomNumber = 0;

	/**
	 * Constructs an empty randomized queue.
	 */
	@SuppressWarnings("unchecked")
	public RandomizedQueue()
	{
		items = (Item[]) new Object[1];
		size = 0;
	}

	/**
	 * Determines whether the queue is empty.
	 * 
	 * @return true if the queue is empty, false otherwise
	 */
	public boolean isEmpty()
	{
		return size == 0;
	}

	/**
	 * Gives the number of items on the queue.
	 * 
	 * @return size of queue
	 */
	public int size()
	{
		return size;
	}

	/**
	 * Adds an item to the queue.
	 * 
	 * @param newItem the item to be added
	 */
	public void enqueue(Item newItem)
	{
		if (newItem == null)
			throw new NullPointerException("The item is null, unable to enqueue.");

		if (size == items.length)
			resize(items.length * 2);

		items[size] = newItem;
		size++;
	}

	/**
	 * Gives an item at random and removes that item from the array.
	 * 
	 * @return the item to be removed
	 */
	public Item dequeue()
	{
		if (isEmpty())
			throw new NoSuchElementException("The queue is empty, unable to dequeue.");

		randomNumber = StdRandom.uniform(size);
		Item item = items[randomNumber];

		if (randomNumber != size - 1)
			items[randomNumber] = items[size - 1];

		items[size - 1] = null;
		size--;

		if (size > 0 && size == items.length / 4)
			resize(this.items.length / 2);

		return item;
	}

	/**
	 * Gives a random item without removing it from the queue.
	 * 
	 * @return random item
	 */
	public Item sample()
	{
		if (isEmpty())
		{
			throw new NoSuchElementException("The queue is empty, unable to dequeue.");
		}
		randomNumber = StdRandom.uniform(size);
		Item newItem = items[randomNumber];
		return newItem;
	}

	/**
	 * Doubles the size of the array.
	 * 
	 * @param newSize twice the length of the existing array
	 */
	private void resize(int newSize)
	{
		@SuppressWarnings("unchecked")
		Item[] newItems = (Item[]) new Object[newSize];
		for (int i = 0; i < size; i++)
			newItems[i] = items[i];
		items = newItems;
		newItems = null;
	}

	public Iterator<Item> iterator()
	{
		return new RandomizedQueueIterator();
	}

	private class RandomizedQueueIterator implements Iterator<Item>
	{
		@SuppressWarnings("unchecked")
		private Item[] tempItems = (Item[]) new Object[items.length];
		private int tempSize = size;
		private int newRandomNumber = StdRandom.uniform(tempSize);;

		public boolean hasNext()
		{
			return tempSize > 0;
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
				Item item = items[newRandomNumber];

				if (newRandomNumber != tempSize - 1)
					tempItems[newRandomNumber] = tempItems[tempSize - 1];

				tempItems[tempSize - 1] = null;
				tempSize--;
				return item;
			}
		}
	}
}
