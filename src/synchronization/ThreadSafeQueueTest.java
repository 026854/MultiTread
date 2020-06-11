package synchronization;

import java.util.Queue;

public class ThreadSafeQueueTest {
    private static class Producer implements Runnable{
        private ThreadSafeQueue threadSafeQueue;
        private Integer producedCount;

        Producer(ThreadSafeQueue threadSafeQueue, Integer producedCount) {
            this.threadSafeQueue = threadSafeQueue;
            this.producedCount = producedCount;
        }

        @Override
        public void run() {
            synchronized (producedCount) {
                this.producedCount ++;
                try {
                    System.out.println("enqueue" + this.producedCount);
                    threadSafeQueue.enqueue(this.producedCount);
                } catch (InterruptedException e) {
                    System.out.println("enqueue failed");
                }
            }
        }
    }

    private static class Consumer implements Runnable{
        private ThreadSafeQueue threadSafeQueue;
        private Integer cosumedCount;

        Consumer(ThreadSafeQueue threadSafeQueue, Integer cosumedCount) {
            this.threadSafeQueue = threadSafeQueue;
            this.cosumedCount = cosumedCount;
        }

        @Override
        public void run() {
            synchronized (cosumedCount) {
                this.cosumedCount ++;
                try {
                    System.out.println("dequeue" + threadSafeQueue.dequeue());
                } catch (InterruptedException e) {
                    System.out.println("dequeue failed");
                }
            }
        }

    }
    public static void main(String[] args) {
        ThreadSafeQueue threadSafeQueue = new ThreadSafeQueue(10);
        Integer producerCount = Integer.valueOf(0);
        Integer consumerCount = Integer.valueOf(0);

        Producer p1 = new Producer(threadSafeQueue, producerCount);
        Producer p2 = new Producer(threadSafeQueue, producerCount);
        Producer p3 = new Producer(threadSafeQueue, producerCount);

        Consumer c1 = new Consumer(threadSafeQueue, consumerCount);
        Consumer c2 = new Consumer(threadSafeQueue, consumerCount);

        Thread t1 = new Thread(p1);
        Thread t2 = new Thread(p2);
        Thread t3 = new Thread(p3);
        Thread t4 = new Thread(c1);
        Thread t5 = new Thread(c2);



        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();




    }
}
