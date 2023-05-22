package com.xuben.hot100;

public class KMP {

    public static void main(String[] args) {

    }

    public static int strStr(String haystack, String needle) {

        int m = haystack.length();
        int n = needle.length();

        haystack = " " + haystack;
        needle = " " + needle;

        char[] ss = haystack.toCharArray();
        char[] pp = needle.toCharArray();

        int[] next = new int[n + 1];

        for (int i = 2, j = 0; i <= n; i++) {
            while (j > 0 && pp[i] != pp[j + 1]){
                j = next[j];
            }

            if (pp[i] == pp[j + 1]) {
                j++;
            }
            next[i] = j;
        }

        for (int i = 1, j = 0; i <= m ; i++) {
            while (j > 0 && ss[i] != pp[j + 1]){
                j = next[j];
            }

            if (ss[i] == pp[j + 1]) {
                j++;
            }
            if (j == n) {
                return i - n;
            }
        }

        return -1;
    }
}
