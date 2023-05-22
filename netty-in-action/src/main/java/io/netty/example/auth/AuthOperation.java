package io.netty.example.auth;

import io.netty.example.common.Operation;
import io.netty.example.common.OperationResult;
import lombok.Data;
import lombok.extern.java.Log;

@Data
@Log
public class AuthOperation extends Operation {
    private String username;
    private String password;

    @Override
    public OperationResult execute() {
        if ("admin".equalsIgnoreCase(this.username)) {
            return new AuthOperationResult(true);
        }
        return new AuthOperationResult(false);
    }
}
