package org.example.task1;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Main {
    enum caseToTest {HashMap, ConcurrentHashMap, SynchronizedMap, ThreadSafeMap};
    public static void main(String[] args) {

        Map<Integer, Integer> map = testTask1(caseToTest.SynchronizedMap);
        Thread writerThread = new Thread(new SyncWriterRunnable(map));
        Thread readerThread = new Thread(new SyncReaderRunnable(map));
        writerThread.start();
        readerThread.start();

    }

    private static Map<Integer, Integer> testTask1(caseToTest caseToTest) {
        switch (caseToTest) {
            case HashMap -> {
                return new HashMap<>();
            }
            case ConcurrentHashMap -> {
                return new ConcurrentHashMap<>();
            }
            case SynchronizedMap -> {
                return Collections.synchronizedMap(new HashMap<>());
            }
            case ThreadSafeMap -> {
                return new ThreadSafeMap();
            }
            default -> {
                return null;
            }
        }
    }
}