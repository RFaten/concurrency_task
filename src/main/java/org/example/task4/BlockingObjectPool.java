package org.example.task4;

import java.util.LinkedList;
import java.util.List;

/**
 * Pool that block when it has not any items or it full

 */
public class BlockingObjectPool {
    private final List<Object> pool = new LinkedList<>();
    private final int size;
    /**
     * Creates filled pool of passed size
     *
     * @param size of pool
     */
    public BlockingObjectPool(int size) {
        this.size = size;
        for (int i = 0; i < size; i++) {
            pool.add(new Object());
        }

    }

    /**
     * Gets object from pool or blocks if pool is empty
     *
     * @return object from pool
     */
    public synchronized Object get() throws InterruptedException {
        while (pool.isEmpty()) {
            wait();
        }
        Object object = pool.removeFirst();
        notifyAll();
        return object;
    }

    /**
     * Puts object to pool or blocks if pool is full
     *
     * @param object to be taken back to pool
     */
    public synchronized void take(Object object) throws InterruptedException {
        while (pool.size() == size) {
            wait();
        }
        pool.add(object);
        notifyAll();
    }
}
