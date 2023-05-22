package io.netty.example.util;

import java.util.concurrent.atomic.AtomicLong;

public class IdUtil {
    private static final AtomicLong IDX = new AtomicLong();

    private IdUtil(){
        //No Instance
    }

    public static long nextId(){
        return IDX.incrementAndGet();
    }
}
