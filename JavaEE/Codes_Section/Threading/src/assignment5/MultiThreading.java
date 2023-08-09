package assignment5;

import java.util.ArrayList;
import java.util.List;

class Producer implements Runnable {
    private List<Integer> buffer;
    private int maxSize;
    public Producer(List<Integer> buffer, int maxSize) {
        this.buffer = buffer;
        this.maxSize = maxSize;
    }
    @Override
    public void run() {
        for (int i = 0; i < 11; i++) {
            synchronized (buffer) {
                while (buffer.size() == maxSize) {
                    try {
                        System.out.println("Buffer is full, waiting...");
                        buffer.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                buffer.add(i);
                System.out.println("Produced " + i);
                buffer.notifyAll();
            }
        }
    }
}
class Consumer implements Runnable {
    private List<Integer> buffer;
    public Consumer(List<Integer> buffer) {
        this.buffer = buffer;
    }
    @Override
    public void run() {
        while (true) {
           synchronized (buffer) {
                while (buffer.isEmpty()) {
                    try {
                        System.out.println("Buffer is empty, waiting...");
                        buffer.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                int value = buffer.remove(0);
                System.out.println("Consumed " + value);
                buffer.notifyAll();
            }
        }
    }
}

public class MultiThreading {
    public static void main(String[] args) {
        List<Integer> buffer = new ArrayList<>();
        int maxSize = 5;

        Producer producer = new Producer(buffer, maxSize);
        //Producer producer1 = new Producer(buffer, maxSize);
        Consumer consumer = new Consumer(buffer);

        Thread producerThread = new Thread(producer);
        //Thread producerThread1 = new Thread(producer1);
        Thread consumerThread = new Thread(consumer);

        producerThread.start();
        consumerThread.start();
        //producerThread1.start();
        //producerThread.run();
        //consumerThread.run();
    }
}
