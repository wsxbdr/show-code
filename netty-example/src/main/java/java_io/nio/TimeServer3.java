package java_io.nio;

/**
 * @author xuben
 * @description TODO
 * @date 2023/5/22 14:44
 */
public class TimeServer3 {
  private static final int PORT = Integer.parseInt(System.getProperty("port", "8003"));

  public static void main(String[] args) {
    MultiplexerTimeServer multiplexerTimeServer = new MultiplexerTimeServer(PORT);

    new Thread(multiplexerTimeServer, "NIO-001").start();
  }

}
