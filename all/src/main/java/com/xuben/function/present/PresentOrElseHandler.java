package com.xuben.function.present;

import java.util.Objects;
import java.util.function.Consumer;

public interface PresentOrElseHandler<T extends Object> {

    void presentOrElseHandle(Consumer<? super T> action, Runnable emptyAction);
}
