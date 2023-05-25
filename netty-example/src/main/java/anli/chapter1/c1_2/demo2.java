package anli.chapter1.c1_2;

import java.util.Locale;
import java.util.concurrent.TimeUnit;
import sun.misc.Signal;
import sun.misc.SignalHandler;

/**
 * @author xuben
 * @description TODO
 * @date 2023/5/22 17:08
 */
public class demo2 {

  public static void main(String[] args) {
    Signal sig = new Signal(getOSSignalType());
    Signal.handle(sig, new ShutdownHandler());

    try {
      TimeUnit.SECONDS.sleep(3);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
//    System.exit(1);
  }

  private static String getOSSignalType() {
    return System.getProperty("os.name").toLowerCase(Locale.ROOT).startsWith("win") ? "INT" : "TERM";
  }

  private static class ShutdownHandler implements SignalHandler {

    @Override
    public void handle(Signal signal) {
      System.out.println("ShutdownHook execute start...");
      System.out.println("Netty NioEventLoopGroup shutdownGracefully...");
      try {
        TimeUnit.SECONDS.sleep(1);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      System.out.println("ShutdownHook execute end...");
    }
  }
}
