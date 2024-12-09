package org.example.task4;

public class Main {
    public static void main(String[] args) {
        BlockingObjectPool pool = new BlockingObjectPool(3);
        Runnable runnable = new TestBlockingObjectPoolRunnable(pool);
        Thread thread1 = new Thread(runnable);
        Thread thread2 = new Thread(runnable);
        Thread thread3 = new Thread(runnable);
        Thread thread4 = new Thread(runnable);

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();

    }
}
