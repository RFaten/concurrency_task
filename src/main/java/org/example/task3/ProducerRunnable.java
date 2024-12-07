package org.example.task3;

import java.util.Random;

public class ProducerRunnable implements Runnable {
    private final MessageQueue queue;

    public ProducerRunnable(MessageQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        Random random = new Random();
        while (true) {
            try {
                Thread.sleep(1000); // Producing message
                String message = "Message of " +random.nextInt();
                queue.enqueue(message);
                System.out.println("Producer produced: " + message);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }
}
