package com.xuben.test;

import java.util.concurrent.TimeUnit;

public class ShutDownTest {
    public static void main(String[] args) throws InterruptedException {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("ShutdownHook execute start ... ");
            System.out.println("Netty NioEventLoopGroup shutdownGracefully...");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("ShutdownHook execute end...");
        }, ""));
        TimeUnit.SECONDS.sleep(7);
        System.exit(0);
    }
}
