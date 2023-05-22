package com.xuben.function;

import com.xuben.function.branch.BranchHandle;
import com.xuben.function.present.PresentOrElseHandler;

public class FUtils {

    public static BranchHandle isTrueOrFalse(Boolean b) {
        return ((trueHandler, falseHandler) -> {
            if (b) {
                trueHandler.run();
            } else {
                falseHandler.run();
            }
        });
    }

    public static PresentOrElseHandler<?> isEmptyOrElse(String s) {
        return ((action, emptyAction) -> {
            if (s == null || s.length() == 0) {
                emptyAction.run();
            }else {
                action.accept(s);
            }
        });
    }
}
