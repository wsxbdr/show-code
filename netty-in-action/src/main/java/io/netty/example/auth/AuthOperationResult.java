package io.netty.example.auth;

import io.netty.example.common.OperationResult;
import lombok.Data;

@Data
public class AuthOperationResult extends OperationResult {

    private final boolean passAuth;
}
