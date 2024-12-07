package org.example.task2;

import java.util.List;

public class SumCalcRunnable implements Runnable {
    private final List<Integer> numbers;

    public SumCalcRunnable(List<Integer> numbers) {
        this.numbers = numbers;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (numbers) {
                int sum = numbers.stream().mapToInt(i -> i).sum();
                System.out.println("Sum of the numbers is: " + sum);
            }
        }

    }
}
