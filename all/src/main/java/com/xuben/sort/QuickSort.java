package com.xuben.sort;

public class QuickSort {
    public static void main(String[] args) {

        int[] arr = new int[]{5, 3, 7, 9, 1};

        quickSort(arr, 0, arr.length -1);

        for (int i : arr) {
            System.out.println(i);
        }
    }

    public static int[] quickSort (int[] arr, int left, int right) {
        int len = arr.length;
        int l = left;
        int r = right;

        if (left < right) {
            int partitionIndex = partition(arr, l, r);
            quickSort(arr, l, partitionIndex -1);
            quickSort(arr, partitionIndex + 1, r);
        }

        return arr;
    }

    public static int partition(int[] arr, int left, int right) {

        int pivot = left;
        int index = pivot + 1;

        for (int i = index; i <= right ; i++) {
            if (arr[i] < arr[pivot]) {
                swap(arr, i, index);
                index++;
            }
        }

        swap(arr, pivot, index - 1);

        return index - 1;
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
