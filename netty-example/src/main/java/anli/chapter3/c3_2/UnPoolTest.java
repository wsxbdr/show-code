package anli.chapter3.c3_2;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

/**
 * @author xuben
 * @description TODO
 * @date 2023/5/23 13:58
 */
public class UnPoolTest {

  public static void main(String[] args) {
    unPool();
  }

  public static void unPool() {
    long startTime = System.currentTimeMillis();
    ByteBuf buf = null;
    int maxTimes = 10000000;
    for (int i = 0; i < maxTimes; i++) {
      buf = Unpooled.buffer(10 * 1024);
      buf.release();
    }
    System.out.println("Execute " + maxTimes + " times cost time: " + (System.currentTimeMillis() - startTime));
  }

}
