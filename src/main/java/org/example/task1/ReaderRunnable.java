package org.example.task1;

import java.util.Map;

public class ReaderRunnable implements Runnable {
    private final Map<Integer, Integer> map;

    public ReaderRunnable(Map<Integer, Integer> map) {
        this.map = map;
    }

    @Override
    public void run() {
        while (true) {
            int sum = map.values().stream().mapToInt(i -> i).sum();
            System.out.println("Map values sum is: " + sum);
        }
    }
}
