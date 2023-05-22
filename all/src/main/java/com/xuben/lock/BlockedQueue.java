package com.xuben.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BlockedQueue<T> {
    final Lock lock = new ReentrantLock();

    //条件变量，队列不满
    final Condition notFull = lock.newCondition();

    //条件变量， 队列不空
    final Condition notEmpty = lock.newCondition();

    //入队
    void enq(T x) {
        lock.lock();
        try {
            while (isFull()) {
                //等待队列不满
                notFull.await();
            }
            //省略入队操作
            //。。。。。。。
            //入队后通知出队操作，队列不空
            notEmpty.signal();
        }catch (Exception e) {

        } finally {
            lock.unlock();
        }
    }

    void deq(T x) {
        lock.lock();
        try {
            while (isEmpty()) {
                // 等待队列不空
                notEmpty.await();
            }
            // 省略出队操作
            // 通知出队后通知入队操作，队列不满
            notFull.signal();
        } catch (Exception e) {

        } finally {
            lock.unlock();
        }
    }

    Boolean isFull() {
        //队列已满
        return true;
    }

    Boolean isEmpty() {
        //队列为空
        return false;
    }
}
