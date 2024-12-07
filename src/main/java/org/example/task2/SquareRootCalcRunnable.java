package org.example.task2;

import java.util.List;

public class SquareRootCalcRunnable implements Runnable {
    private final List<Integer> numbers;

    public SquareRootCalcRunnable(List<Integer> numbers) {
        this.numbers = numbers;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (numbers) {
                double sqrt =
                        Math.sqrt(
                                numbers.stream()
                                        .mapToInt(i -> i * i)
                                        .sum());
                System.out.println("Square root of the sum of the numbers squares is: " + sqrt);
            }

        }

    }
}
