package org.example.task1;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class ThreadSafeMap extends HashMap<Integer, Integer> {
    @Override
    public synchronized Integer put(Integer key, Integer value) {
        return super.put(key, value);
    }

    @Override
    public synchronized Collection<Integer> values() {
        return new ArrayList<>(super.values());
    }
}
