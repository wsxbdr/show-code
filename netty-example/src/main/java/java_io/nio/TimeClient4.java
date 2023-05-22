package java_io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Set;

/**
 * @author xuben
 * @description TODO
 * @date 2023/5/22 15:41
 */
public class TimeClient4 {

  private static final String HOST = System.getProperty("host", "127.0.0.1");
  private static final int PORT = Integer.parseInt(System.getProperty("port", "8005"));
  private static volatile boolean stop = true;

  public static void main(String[] args) {

    SocketChannel socketChannel;
    Selector selector;

    try {
      socketChannel = SocketChannel.open();
      socketChannel.configureBlocking(false);
      selector = Selector.open();
      if (socketChannel.connect(new InetSocketAddress(HOST, PORT))) {
        socketChannel.register(selector, SelectionKey.OP_READ);
      } else {
        socketChannel.register(selector, SelectionKey.OP_CONNECT);
      }

     while (!stop) {
       selector.select();
       Set<SelectionKey> selectionKeys = selector.selectedKeys();
       Iterator<SelectionKey> iterator = selectionKeys.iterator();
       SelectionKey key = null;
       while (iterator.hasNext()) {
         key = iterator.next();
         iterator.remove();
         if (key.isValid()) {
           SocketChannel sc = (SocketChannel) key.channel();
           if (key.isConnectable()) {
             if (sc.finishConnect()) {
               sc.register(selector, SelectionKey.OP_READ);
               doWrite(sc);
             } else {
               System.exit(1);
             }
           }

           if (key.isReadable()) {
             ByteBuffer readBuffer = ByteBuffer.allocate(1024);
             int readBytes = sc.read(readBuffer);
             if (readBytes > 0) {
               readBuffer.flip();
               byte[] bytes = new byte[readBuffer.remaining()];
               readBuffer.get(bytes);
               String body = new String(bytes, "UTF-8");
               System.out.println("Now is: " + body);
               stop = false;
             } else if (readBytes < 0) {
               key.cancel();
               sc.close();
             } else {

             }
           }
         }
       }
     }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private static void doWrite(SocketChannel socketChannel) throws IOException {
    byte[] bytes = "query time order".getBytes(StandardCharsets.UTF_8);
    ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);
    writeBuffer.put(bytes);
    writeBuffer.flip();
    socketChannel.write(writeBuffer);
    if (!writeBuffer.hasRemaining()) {
      System.out.println("Send order 3 server succeed!");
    }
  }

}
