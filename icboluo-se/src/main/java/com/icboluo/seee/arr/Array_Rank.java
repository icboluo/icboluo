package com.icboluo.seee.arr;

//数组排序
public class Array_Rank {
    public static void main(String[] args) {
        int[] arr1 = {1, 5, 9, 3, 4, 0, 2, 7};
        int[] arr2 = {1, 5, 9, 3, 4, 0, 2, 7};
        paiXu(arr1);
        selectSort(arr2);

    }

    private static void paiXu(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    private static void selectSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
    }
}