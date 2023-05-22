package io.netty.example.client.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.example.common.RequestMessage;
import io.netty.handler.codec.MessageToMessageEncoder;

import java.util.List;

public class OrderProtocolEncoder extends MessageToMessageEncoder<RequestMessage> {
    protected void encode(ChannelHandlerContext channelHandlerContext, RequestMessage requestMessage, List<Object> list) throws Exception {
        ByteBuf buffer = channelHandlerContext.alloc().buffer();
        requestMessage.encode(buffer);

        list.add(buffer);
    }
}
