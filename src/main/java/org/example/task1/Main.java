package org.example.task1;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Map<Integer, Integer> map = new HashMap<>();
        Thread writerThread = new Thread(new WriterRunnable(map));
        Thread readerThread = new Thread(new ReaderRunnable(map));
        writerThread.start();
        readerThread.start();

    }
}