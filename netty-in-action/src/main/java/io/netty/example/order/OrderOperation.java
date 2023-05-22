package io.netty.example.order;

import io.netty.example.common.Operation;
import io.netty.example.common.OperationResult;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
public class OrderOperation extends Operation {

    private String tableId;
    private String dish;

    @Override
    public OperationResult execute() {
        log.info("order's executing startup with orderRequest" + toString());
        // execute order logic
        log.info("order's executing complete");
        return new OrderOperationResult(tableId, dish, true);
    }
}
