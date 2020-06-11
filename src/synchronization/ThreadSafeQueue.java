package synchronization;

import java.util.LinkedList;
import java.util.Queue;

public class ThreadSafeQueue {
    private Queue<Integer> queue = new LinkedList();
    private final int  limit;

    public ThreadSafeQueue(int limit){
        this.limit = limit;
    }

    public synchronized void enqueue(Integer item)
            throws InterruptedException  {
        while(this.queue.size() == this.limit) {
            wait();
        }
        if(this.queue.size() == 0) {
            notifyAll();
        }
        this.queue.add(item);
    }


    public synchronized Integer dequeue()
            throws InterruptedException{
        while(this.queue.size() == 0){
            wait();
        }
        if(this.queue.size() == this.limit){
            notifyAll();
        }

        return this.queue.poll();
    }

    public static void main(String[] args) {
        ThreadSafeQueue threadSafeQueue = new ThreadSafeQueue(10);

    }
}
