package org.example.task3;

public class Main {
    public static void main(String[] args) {
        MessageQueue queue = new MessageQueue();
        Thread producerThread = new Thread(new ProducerRunnable(queue));
        Thread consumerThread = new Thread(new ConsumerRunnable(queue));

        producerThread.start();
        consumerThread.start();

    }
}
