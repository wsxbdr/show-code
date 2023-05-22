package io.netty.example.client.dispatcher;

import io.netty.example.common.OperationResult;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class RequestPendingCenter {

    private Map<Long, OperationResultFuture> map = new ConcurrentHashMap<Long, OperationResultFuture>();

    public void add(Long streamId, OperationResultFuture future) {
        this.map.put(streamId, future);
    }

    public void set(Long streamId, OperationResult operationResult) {
        OperationResultFuture operationResultFuture = this.map.get(streamId);
        if (Objects.nonNull(operationResultFuture)) {
            operationResultFuture.setSuccess(operationResult);
            this.map.remove(streamId);
        }
    }

}
