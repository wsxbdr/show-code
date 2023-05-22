package io.netty.example.common;

import io.netty.buffer.ByteBuf;
import io.netty.example.util.JsonUtil;
import lombok.Data;

import java.nio.charset.Charset;

@Data
public abstract class Message<T extends MessageBody> {

    private MessageHeader messageHeader;
    private T messageBody;

    public T getMessageBody() {
        return messageBody;
    }

    public void encode(ByteBuf byteBuf) {
        byteBuf.writeInt(messageHeader.getVersion());
        byteBuf.writeInt(messageHeader.getOpCode());
        byteBuf.writeLong(messageHeader.getStreamId());
        byteBuf.writeBytes(JsonUtil.toJson(messageBody).getBytes());
    }

    public abstract Class<T> getMessageBodyDecodeType(int opCode);

    public void decode(ByteBuf message) {
        int version = message.readInt();
        int opCode = message.readInt();
        long streamId = message.readLong();

        MessageHeader messageHeader = new MessageHeader();
        messageHeader.setVersion(version);
        messageHeader.setOpCode(opCode);
        messageHeader.setStreamId(streamId);
        this.messageHeader = messageHeader;

        Class<T> bodyClazz = getMessageBodyDecodeType(opCode);
        T body = JsonUtil.fromJson(message.toString(Charset.forName("UTF-8")), bodyClazz);
        this.messageBody = body;
    }
}
