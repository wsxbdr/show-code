package java_io.nio;

/**
 * @author xuben
 * @description TODO
 * @date 2023/5/22 14:46
 */
public class TimeClient3 {

  private static final int PORT = Integer.parseInt(System.getProperty("port", "8003"));

  public static void main(String[] args) {
    new Thread(new TimeClientHandler("127.0.0.1", PORT), "TimeClient-001").start();
  }
}
