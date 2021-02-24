package com.icboluo.datastructure.sort;

import com.icboluo.util.RandomHelper;

import java.util.Arrays;

/**
 * 选择排序
 *
 * @author icboluo
 */
class SelectSort {
    public static void main(String[] args) {
        int[] arr = {101, 34, 119, 1};
        //selectSort(arr);
        selectSort2(arr);
        selectSort2(RandomHelper.getRandom(80000));
    }

    private static void selectSort2(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            int min = arr[i];
            for (int j = i; j < arr.length; j++) {
                if (min > arr[j]) {
                    min = arr[j];
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                arr[minIndex] = arr[i];
                arr[i] = min;
            }
        }
    }

    private static void selectSort(int[] arr) {
        int minIndex = 0;
        int min = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (min > arr[i]) {
                min = arr[i];
                minIndex = i;
            }
        }
        if (minIndex != 0) {
            arr[minIndex] = arr[0];
            arr[0] = min;
        }
        System.out.println("第一轮后··");
        System.out.println(Arrays.toString(arr));

        minIndex = 1;
        min = arr[1];
        for (int i = 1 + 1; i < arr.length; i++) {
            if (min > arr[i]) {
                min = arr[i];
                minIndex = i;
            }
        }
        if (minIndex != 1) {
            arr[minIndex] = arr[1];
            arr[1] = min;
        }
        System.out.println("第二轮后··");
        System.out.println(Arrays.toString(arr));

        minIndex = 2;
        min = arr[2];
        for (int i = 1 + 2; i < arr.length; i++) {
            if (min > arr[i]) {
                min = arr[i];
                minIndex = i;
            }
        }
        if (minIndex != 2) {
            arr[minIndex] = arr[2];
            arr[2] = min;
        }
        System.out.println("第三轮后··");
        System.out.println(Arrays.toString(arr));
    }
}
