package org.example.task2;

import java.util.List;
import java.util.Random;

public class WriterRunnable implements Runnable {
    private final List<Integer> numbers;

    public WriterRunnable(List<Integer> numbers) {
        this.numbers = numbers;
    }

    @Override
    public void run() {
        Random random = new Random();
        while (true) {
            numbers.add(random.nextInt(0, 1000));
        }
    }
}
