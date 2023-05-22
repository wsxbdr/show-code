package io.netty.example.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.example.server.codec.OrderFrameDecoder;
import io.netty.example.server.codec.OrderFrameEncoder;
import io.netty.example.server.codec.OrderProrocolEncoder;
import io.netty.example.server.codec.OrderProtocolDecoder;
import io.netty.example.server.handler.MetricsHandler;
import io.netty.example.server.handler.OrderServerProcessHanlder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.util.concurrent.DefaultThreadFactory;

import java.util.concurrent.ExecutionException;

public class Server {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        NioEventLoopGroup boss = new NioEventLoopGroup(1, new DefaultThreadFactory("boss"));
        NioEventLoopGroup worker = new NioEventLoopGroup(0, new DefaultThreadFactory("worker"));

        MetricsHandler metricsHandler = new MetricsHandler();

        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(boss, worker)
                .channel(NioServerSocketChannel.class)
                .handler(new LoggingHandler(LogLevel.INFO))
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ChannelPipeline p = ch.pipeline();
                        p.addLast("OrderFrameDecoder", new OrderFrameDecoder());
                        p.addLast("OrderFrameEncoder", new OrderFrameEncoder());
                        p.addLast("OrderProtocolDecoder", new OrderProtocolDecoder());
                        p.addLast("OrderProtocolEncoder", new OrderProrocolEncoder());

                        p.addLast("MetricsHandler", metricsHandler);
                        p.addLast("LoggingHandler", new LoggingHandler(LogLevel.INFO));
                        p.addLast("OrderServerProcessHanlder", new OrderServerProcessHanlder());
                    }
                });

        ChannelFuture future = serverBootstrap.bind(8001).sync();
        future.channel().closeFuture().get();
    }
}
