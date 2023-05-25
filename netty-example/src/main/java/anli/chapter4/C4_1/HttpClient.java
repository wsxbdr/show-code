package anli.chapter4.C4_1;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.util.concurrent.DefaultThreadFactory;

/**
 * @author xuben
 * @description TODO
 * @date 2023/5/23 18:00
 */
public class HttpClient {
  private Channel channel;

  private void connect(String host, int port) throws InterruptedException {
    NioEventLoopGroup group = new NioEventLoopGroup(new DefaultThreadFactory("ClientGroup"));
    Bootstrap bootstrap = new Bootstrap();
    bootstrap.group(group)
        .channel(NioSocketChannel.class)
        .handler(new ChannelInitializer<SocketChannel>() {

          @Override
          protected void initChannel(SocketChannel channel) throws Exception {
            ChannelPipeline p = channel.pipeline();
            p.addLast(new HttpClientCodec());
            p.addLast(new HttpObjectAggregator(Short.MAX_VALUE));

          }
        });
    ChannelFuture future = bootstrap.connect(host, port).sync();
    channel = future.channel();
  }

}
