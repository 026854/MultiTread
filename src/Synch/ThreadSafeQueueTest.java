package Synch;

public class ThreadSafeQueueTest {
	public static void main(String[] args) throws InterruptedException {
		ThreadSafeQueue threadSafeQueue = new ThreadSafeQueue(10);
		Integer producedMessageCounter = Integer.valueOf(0);
		Integer consumedMessageCounter = Integer.valueOf(0);

		Producer producer1 = new Producer(threadSafeQueue, producedMessageCounter, "[threadQueue.Producer 1]");
		Producer producer2 = new Producer(threadSafeQueue, producedMessageCounter, "[threadQueue.Producer 2]");
		Producer producer3 = new Producer(threadSafeQueue, producedMessageCounter, "[threadQueue.Producer 3]");

		Thread producerThread1 = new Thread(producer1);
		Thread producerThread2 = new Thread(producer2);
		Thread producerThread3 = new Thread(producer3);

		Consumer consumer1 = new Consumer(threadSafeQueue, consumedMessageCounter, "[threadQueue.Consumer 1]");
		Consumer consumer2 = new Consumer(threadSafeQueue, consumedMessageCounter, "[threadQueue.Consumer 2]");

		Thread consumerThread1 = new Thread(consumer1);
		Thread consumerThread2 = new Thread(consumer2);

		producerThread1.start();
		producerThread2.start();
		producerThread3.start();

		consumerThread1.start();
		consumerThread2.start();

//		producerThread1.join();
//		producerThread2.join();
//		producerThread3.join();
//
//		consumerThread1.join();
//		consumerThread2.join();

		System.out.println();
		System.out.println("[RESULT] producedMessageCount : " + producedMessageCounter
			+ ", consumedMessageCount : " + consumedMessageCounter);
	}
}