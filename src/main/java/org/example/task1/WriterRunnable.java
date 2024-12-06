package org.example.task1;

import java.util.Map;
import java.util.Random;

public class WriterRunnable implements Runnable {
    private final Map<Integer, Integer> map;

    public WriterRunnable(Map<Integer, Integer> map) {
        this.map = map;
    }

    @Override
    public void run() {
        Random random = new Random();
        while (true) {
            int toAdd = random.nextInt(0, 1000);
            map.put(map.size() + 1, toAdd);
        }
    }
}
