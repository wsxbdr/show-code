package io.netty.example.common;

public class ResponseMessage extends Message<OperationResult>{
    @Override
    public Class getMessageBodyDecodeType(int opCode) {
        return OperationType.fromOpcode(opCode).getOperationResultClazz();
    }
}
