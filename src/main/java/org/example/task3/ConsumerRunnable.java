package org.example.task3;

import java.util.Random;

public class ConsumerRunnable implements Runnable {
    private final MessageQueue queue;

    public ConsumerRunnable(MessageQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        Random random = new Random();
        while (true) {
            try {
                Thread.sleep(500); // Consuming message
                String message = queue.dequeue();
                System.out.println("Consumer consumed: " + message);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }

    }
}
