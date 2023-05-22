package io.netty.example.server.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.example.common.Operation;
import io.netty.example.common.OperationResult;
import io.netty.example.common.RequestMessage;
import io.netty.example.common.ResponseMessage;

public class OrderServerProcessHanlder extends SimpleChannelInboundHandler<RequestMessage> {
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, RequestMessage requestMessage) throws Exception {
//        ByteBuf buffer = channelHandlerContext.alloc().buffer();
        Operation operation = requestMessage.getMessageBody();
        OperationResult operationResult = operation.execute();

        ResponseMessage responseMessage = new ResponseMessage();
        responseMessage.setMessageHeader(requestMessage.getMessageHeader());
        responseMessage.setMessageBody(operationResult);

        channelHandlerContext.writeAndFlush(responseMessage);
    }
}
