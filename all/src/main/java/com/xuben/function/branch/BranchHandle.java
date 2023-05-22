package com.xuben.function.branch;

public interface BranchHandle {

    void trueOrFalseHandler(Runnable trueHandler, Runnable falseHandler);
}
