package a02;

import edu.princeton.cs.algs4.StdRandom;

 class Subset
{

	public static void main(String[] args)
	{
		String[] input = new String[]
		{ "sadf", "fhgeth", "fgherhe", "wrrtbndgnhb", "hello", "my", "name", "is", "Jeff", "how", "are", "you",
				"doing" };
		RandomizedQueue<String> randomQueue = new RandomizedQueue<String>();
		int k = StdRandom.uniform(1, input.length);

		for (int i = 0; i < input.length; i++)
		{
			randomQueue.enqueue(input[i]);
		}
		while (k > 0)
		{
			System.out.print(randomQueue.dequeue() + " : ");
			k--;
		}

	}
}
