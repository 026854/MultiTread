package Synch;

public class Producer implements Runnable {
	private ThreadSafeQueue threadSafeQueue;
	private Counter producedCount;
	private String name;
	private Integer integer;

	Producer(ThreadSafeQueue threadSafeQueue, Integer integer, String name) {
		this.threadSafeQueue = threadSafeQueue;
		this.integer = integer;
		this.name = name;
	}

	public void run() {
			synchronized (integer) {
				try{
					this.integer++;
					System.out.println(this.name + " enqueue :" + this.integer + "\t\t\t\t\t"
						+ "producedCount :"+ this.integer);
					threadSafeQueue.enqueue(this.integer);
				}catch (Exception e){

				}


		}
	}
}
