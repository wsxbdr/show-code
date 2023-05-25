package anli.chapter1.c1_2;

import java.util.concurrent.TimeUnit;

/**
 * @author xuben
 * @description TODO
 * @date 2023/5/22 17:01
 */
public class demo1 {

  public static void main(String[] args) {

    Runtime.getRuntime().addShutdownHook(new Thread(() -> {
      System.out.println("ShutdownHook execute start...");
      System.out.println("Netty NioEventLoopGroup shutdownGracefully...");
      try {
        TimeUnit.SECONDS.sleep(3);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      System.out.println("ShutdownHook execute end...");
    }, "t-1"));

    try {
      TimeUnit.SECONDS.sleep(7);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    System.exit(0);
  }

}
