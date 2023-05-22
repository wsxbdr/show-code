package com.xuben.function;

public class FunctionApplication {
    public static void main(String[] args) {
        FUtils.isTrueOrFalse(1>2).trueOrFalseHandler(() -> {
            System.out.println("true");
        }, () -> {
            System.out.println("false");
        });

        FUtils.isEmptyOrElse("").presentOrElseHandle(s -> {
            System.out.println(s.toString().toUpperCase());
        }, () -> {
            System.out.println("字符串为空");
        });
    }
}
