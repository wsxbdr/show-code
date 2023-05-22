package com.xuben.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Cache<K, V> {
    final Map<K, V> cache = new HashMap<>();

    final ReadWriteLock rwl = new ReentrantReadWriteLock();

    final Lock r = rwl.readLock();
    final Lock w = rwl.writeLock();

    V get(K key) {
        w.lock();
        try {
            return cache.get(key);
        }finally {
            w.unlock();
        }
    }

    V put(K key, V value) {
        r.lock();
        try{
            return cache.put(key, value);
        } finally {
            r.unlock();
        }
    }
}
