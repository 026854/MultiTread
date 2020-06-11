package Synch;

public class Consumer implements Runnable {
	private ThreadSafeQueue threadSafeQueue;
	private Counter consumedCount;
	private String name;
	private Integer integer;

	Consumer(ThreadSafeQueue threadSafeQueue, Integer integer, String name) {
		this.threadSafeQueue = threadSafeQueue;
		this.integer = integer;
		this.name = name;
	}

	public void run() {
		while (true) {
			synchronized (integer) {
				try {
					Integer dequeueResult = threadSafeQueue.dequeue();

					this.integer++;
					System.out.println(this.name + " dequeue :" + dequeueResult + "\t\t\t\t\t" + "consumedCount :" + this.integer);
				} catch (InterruptedException e) {
					break;
				}
			}
		}
	}
}
