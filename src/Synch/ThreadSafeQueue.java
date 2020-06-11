package Synch;

import java.util.LinkedList;
import java.util.Queue;

public class ThreadSafeQueue {
	private Queue<Integer> queue = new LinkedList();
	private final int limit;

	public ThreadSafeQueue(int limit) {
		this.limit = limit;
	}

	public synchronized void enqueue(Integer item)
		throws InterruptedException {
		if (this.queue.size() == this.limit) {
			//System.out.println("limit exceed. wait for dequeue");
			wait();
		}

		if (this.queue.size() == 0) {
			//System.out.println("queue is empty. notify all");
			notifyAll();
		}

		//System.out.println("add value to queue");
		this.queue.add(item);
	}

	public synchronized Integer dequeue()
		throws InterruptedException {

		if (this.queue.size() == 0) {
			//System.out.println("queue is empty. there is no message to process.");
			long timeoutMilli = 5000;
			long startTime = System.currentTimeMillis();
			wait(timeoutMilli);
			if (System.currentTimeMillis() - startTime >= timeoutMilli) {
				throw new InterruptedException();
			}
		}

		if (this.queue.size() == this.limit) {
			//System.out.println("queue is full. can dequeue");
			notifyAll();
		}

		//System.out.println("remove value in queue");
		return this.queue.remove();
	}
}