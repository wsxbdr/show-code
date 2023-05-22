package com.xuben.lock;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Predicate;

public class GuardedObject<T> {
    T obj;
    final Lock lock = new ReentrantLock();
    final Condition done = lock.newCondition();
    static final Map<Object, GuardedObject> gos = new ConcurrentHashMap<>();

    static <K> GuardedObject create(K key) {
        GuardedObject go = new GuardedObject();
        gos.put(key, go);
        return go;
    }

    static <K, T> void fireEvent(K key, T obj) {
        GuardedObject go = gos.remove(key);
        if (Objects.nonNull(go)) {
            go.onChange(obj);
        }
    }

    T get(Predicate<T> p) {
        lock.lock();
        try {
            while (!p.test(obj)) {
                done.await();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
        return obj;
    }

    void onChange(T obj) {
        lock.lock();
        try {
            this.obj = obj;
            done.signal();
        } finally {
            lock.unlock();
        }
    }
}
