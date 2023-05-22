package io.netty.example.keepalive;

import io.netty.example.common.Operation;
import io.netty.example.common.OperationResult;
import lombok.Data;
import lombok.extern.java.Log;

@Data
@Log
public class KeepaliveOperation extends Operation {
    private long time;

    public KeepaliveOperation() {
        this.time = System.nanoTime();
    }

    @Override
    public OperationResult execute() {
        return new KeepaliveOperationResult(time);
    }
}
