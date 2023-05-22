package io.netty.example.common;

public class RequestMessage extends Message<Operation>{
    @Override
    public Class getMessageBodyDecodeType(int opCode) {
        return OperationType.fromOpcode(opCode).getOperationClazz();
    }

    public RequestMessage() {}

    public RequestMessage(Long streamId, Operation operation) {
        MessageHeader messageHeader = new MessageHeader();
        messageHeader.setStreamId(streamId);
        messageHeader.setOpCode(OperationType.fromOperation(operation));
        this.setMessageHeader(messageHeader);
        this.setMessageBody(operation);
    }
}
