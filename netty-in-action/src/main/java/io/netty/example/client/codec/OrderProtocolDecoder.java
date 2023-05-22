package io.netty.example.client.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.example.common.ResponseMessage;
import io.netty.handler.codec.MessageToMessageDecoder;

import java.util.List;

public class OrderProtocolDecoder extends MessageToMessageDecoder<ByteBuf> {
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        ResponseMessage responseMessage = new ResponseMessage();
        responseMessage.decode(byteBuf);

        list.add(responseMessage);
    }
}
