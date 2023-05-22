package io.netty.example.client.codec;

import io.netty.channel.ChannelHandlerContext;
import io.netty.example.common.Operation;
import io.netty.example.common.RequestMessage;
import io.netty.example.util.IdUtil;
import io.netty.handler.codec.MessageToMessageEncoder;

import java.util.List;

public class OrderOperationToRequestMessageEncoder extends MessageToMessageEncoder<Operation> {

    protected void encode(ChannelHandlerContext channelHandlerContext, Operation operation, List<Object> list) throws Exception {
        RequestMessage requestMessage = new RequestMessage(IdUtil.nextId(), operation);

        list.add(requestMessage);
    }
}
