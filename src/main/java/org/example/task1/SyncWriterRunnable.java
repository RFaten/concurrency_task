package org.example.task1;

import java.util.Map;
import java.util.Random;

public class SyncWriterRunnable implements Runnable {
    private final Map<Integer, Integer> map;

    public SyncWriterRunnable(Map<Integer, Integer> map) {
        this.map = map;
    }

    @Override
    public void run() {
        Random random = new Random();
        int toAdd;
        while (true) {
            synchronized (map) {
                toAdd = random.nextInt(0, 1000);
            }
            map.put(map.size() + 1, toAdd);
        }
    }
}
