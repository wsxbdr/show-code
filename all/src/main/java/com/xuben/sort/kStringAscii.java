package com.xuben.sort;

import java.util.Arrays;

public class kStringAscii {

    public static void main(String[] args) {

        int index = findKIndex("fAdDAkBbBq", 4);
        System.out.println(index);
    }

    public static int findKIndex(String str, int k) {

        char[] chars = str.toCharArray();
        Arrays.sort(chars);
        int index = k > str.length() ? str.length() : k;
        char b = chars[index - 1];
        return str.indexOf(String.valueOf(b));
    }
}
