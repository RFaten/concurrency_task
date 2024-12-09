package org.example.task4;


public class TestBlockingObjectPoolRunnable implements Runnable {
    private final BlockingObjectPool pool;

    public TestBlockingObjectPoolRunnable(BlockingObjectPool pool) {
        this.pool = pool;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Object object = pool.get();
                System.out.println(Thread.currentThread().getName() + " processing " + object);
                Thread.sleep(1000);
                pool.take(object);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }
}
