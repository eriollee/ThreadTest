package chapter3_4_5_6;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.Condition;

public class NonReentrantLockCustomTest {
    final static NonReentrantLock lock = new NonReentrantLock();
    final static Condition notFull = lock.newCondition();
    final static Condition notEmpty = lock.newCondition();

    final static Queue<String> queue = new LinkedBlockingQueue<>();
    final static int queueSize = 10;

    public static void main(String[] args) {
        Thread producer = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    lock.lock();
                    try {
                        while (queue.size() == queueSize) {
                            notEmpty.await();
                        }
                        queue.add("ele");
                        System.out.println("producer" + queue.size());
                        notFull.signalAll();

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        lock.unlock();
                    }
                }
            }
        });

        Thread consumer = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    lock.lock();
                    try {
                        while (0 == queue.size()) {
                            notFull.await();
                        }
                        String ele = queue.poll();
                        System.out.println("consumer" + queue.size());
                        notEmpty.signalAll();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        lock.unlock();
                    }
                }
            }
        });

        producer.start();
        consumer.start();
    }
}
