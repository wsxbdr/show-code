package com.xuben.sort;

public class maxSort {

    public static void main(String[] args) {

        System.out.println(maxl("abc2234019A334bc"));
    }

    public static int maxl(String str) {
        char[] chars = str.toCharArray();
        int max = 0;

        for (int i = 0; i < chars.length; i++) {
            int tmp = 0;
            if (chars[i] > '9'){
                continue;
            } else {
                tmp = 1;
            }

            for (int j = i + 1; j < chars.length; j++) {
                if (chars[j] <= '9' && chars[j] >= chars[j - 1]) {
                    tmp++;
                } else {
                    break;
                }
            }

            max = Math.max(max, tmp);
        }
        return max;
    }
}
