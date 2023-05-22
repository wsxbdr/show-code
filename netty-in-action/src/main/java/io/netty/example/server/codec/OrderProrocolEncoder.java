package io.netty.example.server.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.example.common.RequestMessage;
import io.netty.example.common.ResponseMessage;
import io.netty.handler.codec.MessageToMessageEncoder;

import java.util.List;

public class OrderProrocolEncoder extends MessageToMessageEncoder<ResponseMessage> {

    protected void encode(ChannelHandlerContext channelHandlerContext, ResponseMessage responseMessage, List<Object> list) throws Exception {
        ByteBuf buffer = channelHandlerContext.alloc().buffer();
        responseMessage.encode(buffer);

        list.add(buffer);
    }
}
