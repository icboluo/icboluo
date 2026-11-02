package com.icboluo.datastructure.sort;

//数组排序
public class Array_Rank {
    static void main(String[] args) {
        int[] arr1 = {1, 5, 9, 3, 4, 0, 2, 7};
        int[] arr2 = {1, 5, 9, 3, 4, 0, 2, 7};
        bubbleSort(arr1);
        selectSort(arr2);

    }

    private static void bubbleSort(int[] arr) {
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
            int minIdx = i;
            int minEle = arr[i];
            for (int j = i + 1; j < arr.length; j++) {
                if (minEle > arr[j]) {
                    minEle = arr[j];
                    minIdx = j;
                }
            }
            if (minIdx != i) {
                arr[minIdx] = arr[i];
                arr[i] = minEle;
            }
        }
    }
}
