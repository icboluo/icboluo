package com.icboluo.datastructure.sort;

import com.icboluo.util.RandomHelper;

import java.util.Arrays;

/**
 * 堆排序,树的应用
 * 堆有大顶堆（升序），小顶堆
 *
 * @author icbolo
 */
 class HeapSort {
    public static void main(String[] args) {
        int[] arr = {4, 6, 8, 5, 9};
        int[] random = RandomHelper.getRandom(8000000);
        m2(random);

    }

    private static void m1(int[] arr) {
        adjustToDaDingDui(arr, 1, arr.length);
        System.out.println(Arrays.toString(arr));
        adjustToDaDingDui(arr, 0, arr.length);
        System.out.println(Arrays.toString(arr));
    }

    private static void m2(int[] arr) {
        int temp = 0;
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            adjustToDaDingDui(arr, i, arr.length);
        }
        for (int j = arr.length - 1; j > 0; j--) {
            temp = arr[j];
            arr[j] = arr[0];
            arr[0] = temp;
            adjustToDaDingDui(arr, 0, j);
        }
        //System.out.println(Arrays.toString(arr));
    }

    /**
     * 将数组调整成为大顶堆
     *
     * @param arr    待调整的数组 {4, 6, 8, 5, 9}
     * @param i      非叶子节点在数组中的索引（就是每一个三角堆的中间节点）
     * @param length 对多少个元素继续调整
     */
    public static void adjustToDaDingDui(int[] arr, int i, int length) {
        //6=arr[1]
        int temp = arr[i];
        //k=3
        for (int k = i * 2 + 1; k < length; k = k * 2 + 1) {
            //max left right index
            if (k + 1 < length && arr[k] < arr[k + 1]) {
                k++;
            }
            //max compaire with mid
            if (arr[k] > temp) {
                arr[i] = arr[k];
                i = k;
            } else {
                break;
            }
        }
        arr[i] = temp;
    }
}
