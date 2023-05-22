package io.netty.example.keepalive;

import io.netty.example.common.OperationResult;
import lombok.Data;

@Data
public class KeepaliveOperationResult extends OperationResult {
    private final long time;
}
