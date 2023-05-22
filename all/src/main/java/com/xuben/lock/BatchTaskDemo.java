package com.xuben.lock;

import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BatchTaskDemo {
    public static void main(String[] args) {

        ExecutorService executor = Executors.newFixedThreadPool(3);

        CompletionService<Integer> cs = new ExecutorCompletionService<>(executor);

        cs.submit(() -> {
            System.out.println("a");
            return 1;
        });
    }

}
