package io.netty.example.order;

import io.netty.example.common.OperationResult;
import lombok.Data;

@Data
public class OrderOperationResult extends OperationResult {
    private final String tableId;
    private final String dish;
    private final boolean success;
}
