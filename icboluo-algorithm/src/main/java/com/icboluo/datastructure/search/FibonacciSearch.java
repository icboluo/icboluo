package com.icboluo.datastructure.search;

import java.util.Arrays;

/**
 * 斐波那契（黄金分割法）查找算法
 *
 * @author icboluo
 * @since 2020/6/13 16:56
 */
class FibonacciSearch {
    private static final int MAX_SIZE = 20;

    public static void main(String[] args) {

        int[] arr1 = {1, 1, 2, 3, 5, 8, 13, 21, 34, 55};
        int[] arr = {1, 8, 10, 89, 1000, 1234};
        System.out.println(m1(arr, 1));
    }

    private static int m1(int[] arr, int key) {
        //左索引
        int left = 0;
        //右索引
        int right = arr.length - 1;
        //斐波那契分割数值下标
        int k = 0;
        int mid = 0;
        int[] f = fib();
        while (right > f[k] - 1) {
            k++;
        }
        int[] temp = Arrays.copyOf(arr, f[k]);
        for (int i = right + 1; i < temp.length; i++) {
            temp[i] = arr[right];
        }
        while (left < right) {
            mid = right + f[k - 1] - 1;
            if (key < temp[mid]) {
                right = mid - 1;
                k--;
            } else if (key > temp[mid]) {
                left = mid + 1;
                k -= 2;
            } else {
                return Math.min(mid, right);
            }
        }
        return -1;
    }

    /**
     * @return 斐波那契数列
     */
    private static int[] fib() {
        int[] f = new int[MAX_SIZE];
        f[0] = 0;
        f[1] = 1;
        for (int i = 2; i < MAX_SIZE; i++) {
            f[i] = f[i - 1] + f[i - 2];
        }
        return f;
    }
}
