package org.example.task3;

import java.util.LinkedList;
import java.util.List;

public class MessageQueue {
    private final List<String> queue = new LinkedList<>();
    public synchronized void enqueue(String message) {
        queue.add(message);
        notifyAll();
    }
    public synchronized String dequeue() throws InterruptedException {
        while (queue.isEmpty()) {
            wait();
        }
        return queue.removeFirst();
    }
}
