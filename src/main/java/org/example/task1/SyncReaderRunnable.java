package org.example.task1;

import java.util.Map;

public class SyncReaderRunnable implements Runnable {
    private final Map<Integer, Integer> map;

    public SyncReaderRunnable(Map<Integer, Integer> map) {
        this.map = map;
    }

    @Override
    public void run() {
        int sum;
        while (true) {
            synchronized (map) {
                sum = map.values().stream().mapToInt(i -> i).sum();
            }
            System.out.println("Map values sum is: " + sum);
        }
    }
}
