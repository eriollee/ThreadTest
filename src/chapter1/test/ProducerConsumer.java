package chapter1.test;

import chapter1.thread.Producer;
import chapter1.thread.Consumer;
import java.util.LinkedList;
import java.util.Queue;


public class ProducerConsumer {
    public static void main(String args[]) {
        System.out.println("How to use wait and notify method in Java");
        System.out.println("Solving Producer Consumper Problem");

        Queue<Integer> buffer = new LinkedList<>();
        int maxSize = 10;

        Thread producer = new Producer(buffer, maxSize, "PRODUCER");
        Thread consumer = new Consumer(buffer, maxSize, "CONSUMER");

        producer.start();
        consumer.start();

    }
}
