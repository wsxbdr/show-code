package anli.chapter1.c1_1.daemon;

import java.util.concurrent.TimeUnit;

/**
 * @author xuben
 * @description TODO
 * @date 2023/5/22 16:48
 */
public class demo1 {

  public static void main(String[] args) throws InterruptedException {
    long startTime = System.nanoTime();

    Thread t = new Thread(() -> {
      try {
        TimeUnit.DAYS.sleep(Long.MAX_VALUE);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }, "Daemon-T");

    t.setDaemon(true);
    t.start();

    TimeUnit.SECONDS.sleep(15);

    System.out.println("System exit, run time: " + (System.nanoTime() - startTime) / 1000 / 1000 / 1000 + " s");

  }

}
