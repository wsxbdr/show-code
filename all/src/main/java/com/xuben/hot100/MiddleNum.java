package com.xuben.hot100;

public class MiddleNum {
    public static void main(String[] args) {

        int[] nums1 = {0, 0};
        int[] nums2 = {0, 0};
        double i = find(nums1, nums2);
        System.out.println(i);
    }

    public static double find(int[] nums1, int[] nums2) {
        int i = 0;
        int j = 0;
        int left = -1, right = -1;
        int m = nums1.length;
        int n = nums2.length;
        int len = m + n;

        for (int k = 0; k <= len/2; k++) {
            left = right;
            if (i < m && (j > n -1 || nums1[i] < nums2[j])) {
                right = nums1[i++];
            } else {
                right = nums2[j++];
            }
        }

        if (len % 2 == 0){
            return (left + right)/2.0;
        }
        return right;
    }
}
