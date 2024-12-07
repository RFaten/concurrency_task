package org.example.task2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> numbers = Collections.synchronizedList(new ArrayList<>());

        Thread writerThread = new Thread(new WriterRunnable(numbers));
        Thread sumCalcThread = new Thread(new SumCalcRunnable(numbers));
        Thread squareRootCalcThread = new Thread(new SquareRootCalcRunnable(numbers));

        writerThread.start();
        sumCalcThread.start();
        squareRootCalcThread.start();

    }
}
