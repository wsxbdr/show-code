package com.xuben.sort;

public class TXText {
    public static void main(String[] args) {

        char[] chars = new char[]{'+', '+', '-', '+', '-', '+'};
        test(chars);
        for (char c : chars) {
            System.out.print(c);
        }
    }

    public static void test(char[] chars) {
        int i = 0;
        int j = chars.length -1;

        while (i < j) {
            if(chars[i] != chars[j] && chars[i] == '-') {
                char temp = chars[i];
                chars[i] = chars[j];
                chars[j] = temp;
                i++;
                j--;
            }
            i++;
        }
    }
}
