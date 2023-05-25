package anli.chapter2.c2_1;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * @author xuben
 * @description TODO
 * @date 2023/5/23 10:12
 */
public class ClientPool {

  public static void main(String[] args) {
    try {
      initClientPool(100);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public static void initClientPool(int size) throws InterruptedException {
    NioEventLoopGroup group = new NioEventLoopGroup();
    try {
      Bootstrap bootstrap = new Bootstrap();
      bootstrap.group(group)
          .channel(NioSocketChannel.class)
          .handler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel ch) throws Exception {
              ChannelPipeline p = ch.pipeline();

              p.addLast(new LoggingHandler(LogLevel.INFO));
            }
          });

      for (int i = 0; i < size; i++) {
        ChannelFuture future = bootstrap.connect("127.0.0.1", 8004).sync();
//        future.channel().closeFuture().sync();
        future.channel().writeAndFlush(new Object());
      }
    } finally {
      group.shutdownGracefully();
    }

  }

}
