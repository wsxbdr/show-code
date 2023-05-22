package io.netty.example.common;

import com.google.common.base.Objects;
import io.netty.example.auth.AuthOperation;
import io.netty.example.auth.AuthOperationResult;
import io.netty.example.keepalive.KeepaliveOperation;
import io.netty.example.keepalive.KeepaliveOperationResult;
import io.netty.example.order.OrderOperation;
import io.netty.example.order.OrderOperationResult;

public enum OperationType {

    AUTH(1, AuthOperation.class, AuthOperationResult.class),
    KEEPALIVE(2, KeepaliveOperation.class, KeepaliveOperationResult.class),
    ORDER(3, OrderOperation.class, OrderOperationResult.class);

    private int opCode;
    private Class<? extends Operation> operationClazz;
    private Class<? extends OperationResult> operationResultClazz;

    OperationType(int opCode, Class<? extends Operation> operationClazz,
                  Class<? extends OperationResult> operationResultClazz) {
        this.opCode = opCode;
        this.operationClazz = operationClazz;
        this.operationResultClazz = operationResultClazz;
    }

    public int getOpCode() {
        return opCode;
    }

    public Class<? extends Operation> getOperationClazz() {
        return operationClazz;
    }

    public Class<? extends OperationResult> getOperationResultClazz() {
        return operationResultClazz;
    }

    public static OperationType fromOpcode(int opCode) {
        for (OperationType op : OperationType.values()) {
            if (op.opCode == opCode) {
                return op;
            }
        }
        throw new RuntimeException("OperationType is not exist");
    }

    public static int fromOperation(Operation operation) {
        for (OperationType op : OperationType.values()) {
            if (Objects.equal(op.operationClazz, operation.getClass())) {
                return op.opCode;
            }
        }
        throw new RuntimeException("OperationType is not exist");
    }
}
