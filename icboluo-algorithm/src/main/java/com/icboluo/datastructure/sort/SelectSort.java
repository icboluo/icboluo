package com.icboluo.datastructure.sort;

import com.icboluo.util.RandomUtil;

import java.util.Arrays;

/**
 * 选择排序：找到未排序部分的最小元素，将该元素与未排序部分的第一个元素交换
 *
 * @author icboluo
 */
class SelectSort {
    public static void main(String[] args) {
        int[] arr = {101, 34, 119, 1};
        //selectSort(arr);
        selectSort2(arr);
        selectSort2(RandomUtil.getRandom(80000));
    }

    private static void selectSort2(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minIdx = i;
            int minEle = arr[i];
            for (int j = i; j < arr.length; j++) {
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

    private static void selectSort(int[] arr) {
        int minIdx = 0;
        int minEle = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (minEle > arr[i]) {
                minEle = arr[i];
                minIdx = i;
            }
        }
        if (minIdx != 0) {
            arr[minIdx] = arr[0];
            arr[0] = minEle;
        }
        System.out.println("第一轮后··");
        System.out.println(Arrays.toString(arr));

        minIdx = 1;
        minEle = arr[1];
        for (int i = 1 + 1; i < arr.length; i++) {
            if (minEle > arr[i]) {
                minEle = arr[i];
                minIdx = i;
            }
        }
        if (minIdx != 1) {
            arr[minIdx] = arr[1];
            arr[1] = minEle;
        }
        System.out.println("第二轮后··");
        System.out.println(Arrays.toString(arr));

        minIdx = 2;
        minEle = arr[2];
        for (int i = 1 + 2; i < arr.length; i++) {
            if (minEle > arr[i]) {
                minEle = arr[i];
                minIdx = i;
            }
        }
        if (minIdx != 2) {
            arr[minIdx] = arr[2];
            arr[2] = minEle;
        }
        System.out.println("第三轮后··");
        System.out.println(Arrays.toString(arr));
    }
}
