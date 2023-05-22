package java_io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

/**
 * @author xuben
 * @description TODO
 * @date 2023/5/22 15:18
 */
public class TimeServer4 {

  private static final int PORT = Integer.parseInt(System.getProperty("port", "8005"));

  public static void main(String[] args) {
    ServerSocketChannel ssc = null;
    Selector selector = null;

    try {
      ssc = ServerSocketChannel.open();
      ssc.configureBlocking(false);
      ssc.bind(new InetSocketAddress(PORT), 1024);
      selector = Selector.open();
      ssc.register(selector, SelectionKey.OP_ACCEPT);
      System.out.println("The time server is start in port: " + PORT);

      while (true) {
        selector.select(1000);
        Set<SelectionKey> selectionKeys = selector.selectedKeys();
        Iterator<SelectionKey> iterator = selectionKeys.iterator();
        SelectionKey key = null;
        while (iterator.hasNext()) {
          key = iterator.next();
          iterator.remove();
          if (key.isValid()) {
            if (key.isAcceptable()) {
              ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
              SocketChannel sc = serverSocketChannel.accept();
              sc.configureBlocking(false);
              sc.register(selector, SelectionKey.OP_READ);
            }

            if (key.isReadable()) {
              SocketChannel socketChannel = (SocketChannel) key.channel();
              ByteBuffer readBuffer = ByteBuffer.allocate(1024);
              int readBytes = socketChannel.read(readBuffer);
              if (readBytes > 0) {
                readBuffer.flip();
                byte[] bytes = new byte[readBuffer.remaining()];
                readBuffer.get(bytes);
                String body = new String(bytes, "UTF-8");
                System.out.println("The time server receive order: " + body);
                String resp = "query time order".equalsIgnoreCase(body) ? new Date(System.currentTimeMillis()).toString() : "bad query";
                if (resp != null && resp.trim().length() > 0) {
                  byte[] writeBytes = resp.getBytes(StandardCharsets.UTF_8);
                  ByteBuffer writeBuffer = ByteBuffer.allocate(writeBytes.length);
                  writeBuffer.put(writeBytes);
                  writeBuffer.flip();
                  socketChannel.write(writeBuffer);
                }
              } else if (readBytes < 0) {
                key.cancel();
                socketChannel.close();
              } else {
                // Ignore
              }
            }
          }
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
