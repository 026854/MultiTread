package synchronization;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;

class PrintDemo {
    public void printCount(){
        try{
            for(int i=5; i>0;i--){
                System.out.println("Counter ====" + i);
            }
        }catch (Exception e){
            System.out.println("Thread interrupted");
        }
    }
}
class Producer implements Runnable{
    private Queue<Integer> queue;
    private Integer proCnt;
    public Producer(Queue queue,Integer cnt){
        this.queue = queue;
        this.proCnt = cnt;
    }

    @Override
    public void run() {
        synchronized (queue){
            queue.offer(1);
            System.out.println("produced");
            proCnt++;
        }

    }
}
class Consumer implements Runnable{
    private Queue<Integer> queue;
    private Integer conCnt;

    public Consumer(Queue queue, Integer cnt){
        this.queue = queue;
        this.conCnt = cnt;
    }

    @Override
    public void run() {
        synchronized (queue){
            System.out.println(queue.poll()+"consumed");
            //queue.remove();
            conCnt++;
        }
    }
}

class ThreadDemo extends Thread {
    private Thread t;
    private String threadName;
    PrintDemo pd;

    ThreadDemo(String name, PrintDemo pd){
        threadName = name;
        this.pd= pd;
    }

    @Override
    public void run() {
        synchronized (pd) {
            pd.printCount();
        }
        System.out.println("Thread" + threadName + "exiting");
    }

    public void start(){
        System.out.println("Starting " + threadName);
        if(t == null) {
            t= new Thread(this, threadName);
            t.start();
        }
    }
}
public class TestThread {
    private static Queue<Integer> mainQueue = new LinkedList<>();
    private static Integer producerCnt=0 ;
    private static Integer consumerCnt=0 ;
    public static void main(String[] args) {


        Producer p1 = new Producer(mainQueue,producerCnt);
        Producer p2 = new Producer(mainQueue,producerCnt);
        Producer p3 = new Producer(mainQueue,producerCnt);

        Consumer c1 = new Consumer(mainQueue,consumerCnt);
        Consumer c2 = new Consumer(mainQueue,consumerCnt);
        p1.run();
        p2.run();
        p3.run();

        c1.run();
        c2.run();

        System.out.println("pro : " +producerCnt+ "  con" + consumerCnt);

    }
}
