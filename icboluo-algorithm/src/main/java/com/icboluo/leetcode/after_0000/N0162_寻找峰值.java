package com.icboluo.leetcode.after_0000;

/**
 * @author icboluo
 * @since 2022-10-10 21:41
 */
class N0162_寻找峰值 {
    public static void main(String[] args) {
        N0162_寻找峰值 cla = new N0162_寻找峰值();
        int[] arr = {1, 2, 3, 1};
        int i = cla.m1(arr);
        System.out.println("i = " + i);
    }

    /**
     * 线性扫描
     *
     * @param arr
     * @return
     */
    private int m1(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > arr[i + 1]) {
                return i;
            }
        }
        return arr.length - 1;
    }

    /**
     * 二分查找
     *
     * @param arr
     * @return
     */
    private int m2(int[] arr) {
        int left = 0;
        int right = arr.length - 1;
        while (left < right) {
            int mid = (left + right) / 2;
            if (arr[mid] > arr[mid + 1]) {
                right = mid;
            }else{
                left = mid + 1;
            }
        }
        return left;
    }
}
