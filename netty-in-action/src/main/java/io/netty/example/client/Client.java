package io.netty.example.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.example.client.codec.*;
import io.netty.example.client.dispatcher.OperationResultFuture;
import io.netty.example.client.dispatcher.RequestPendingCenter;
import io.netty.example.client.dispatcher.ResponseDispatcherHandler;
import io.netty.example.common.OperationResult;
import io.netty.example.common.RequestMessage;
import io.netty.example.order.OrderOperation;
import io.netty.example.util.IdUtil;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.util.concurrent.DefaultThreadFactory;

import java.util.concurrent.ExecutionException;

public class Client {

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        RequestPendingCenter pendingCenter = new RequestPendingCenter();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(new NioEventLoopGroup(0, new DefaultThreadFactory("clientGroup")));
        bootstrap.channel(NioSocketChannel.class);
        bootstrap.handler(new ChannelInitializer<NioSocketChannel>() {
            protected void initChannel(NioSocketChannel ch) throws Exception {
                ChannelPipeline p = ch.pipeline();
                p.addLast("OrderFrameDecoder", new OrderFrameDecoder());
                p.addLast("OrderFrameEncoder", new OrderFrameEncoder());
                p.addLast("OrderProtocolDecoder", new OrderProtocolDecoder());
                p.addLast("OrderProtocolEncoder", new OrderProtocolEncoder());

                p.addLast("ResponseDispatcherHandler", new ResponseDispatcherHandler(pendingCenter));
                p.addLast("LoggingHandler", new LoggingHandler(LogLevel.INFO));
                p.addLast("OrderOperationToRequestMessageEncoder", new OrderOperationToRequestMessageEncoder());
            }
        });


        ChannelFuture future = bootstrap.connect("127.0.0.1", 8001).sync();

        long streamId = IdUtil.nextId();
        RequestMessage requestMessage = new RequestMessage(streamId, new OrderOperation("1001", "todou"));
//        OrderOperation operation = new OrderOperation("1001", "todou");
        OperationResultFuture operationResultFuture = new OperationResultFuture();
        pendingCenter.add(streamId, operationResultFuture);

        future.channel().writeAndFlush(requestMessage);

        OperationResult operationResult = operationResultFuture.get();
        System.out.println(operationResult);

        future.channel().closeFuture().get();
    }
}
