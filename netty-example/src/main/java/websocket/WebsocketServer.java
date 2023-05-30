package websocket;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.util.concurrent.DefaultThreadFactory;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import lombok.extern.slf4j.Slf4j;

/**
 * @author xuben
 * @description TODO
 * @date 2023/5/25 11:50
 */
@Slf4j
public class WebsocketServer {

  private static final int PORT = Integer.parseInt(System.getProperty("port", "8001"));

  private final Map<String, String> map = new ConcurrentHashMap<>();

  private static NioEventLoopGroup bossGroup;
  private static NioEventLoopGroup workGroup;
  private static Channel channel;

  public static void main(String[] args) {
    try {
      bind(PORT);
      channel.closeFuture().addListener((ChannelFutureListener) channelFuture -> {
        bossGroup.shutdownGracefully();
        workGroup.shutdownGracefully();
        log.info(channelFuture.channel().toString() + "channel close");
      });
    } catch (InterruptedException e) {
      e.printStackTrace();
      // catch会消除中断标记
      Thread.currentThread().interrupt();
    }
  }

  private static void bind(int port) throws InterruptedException {
    bossGroup = new NioEventLoopGroup(1, new DefaultThreadFactory("BossGroup"));
    workGroup = new NioEventLoopGroup(new DefaultThreadFactory("WorkGroup"));

    ServerBootstrap serverBootstrap = new ServerBootstrap();
    serverBootstrap.group(bossGroup, workGroup)
        .channel(NioServerSocketChannel.class)
        .option(ChannelOption.SO_BACKLOG, 128)
        .handler(new LoggingHandler(LogLevel.INFO))
        .childHandler(new WebsocketServerInitializer());

    ChannelFuture f = serverBootstrap.bind(port).sync();
    channel =  f.channel();
  }

}
