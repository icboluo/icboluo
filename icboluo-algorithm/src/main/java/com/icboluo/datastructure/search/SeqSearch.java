package com.icboluo.datastructure.search;

/**
 * 线性查找
 *
 * @author icboluo
 * @since 2020/6/12 12:58
 */
class SeqSearch {
    public static void main(String[] args) {
        int[] arr = {1, 9, 11, -1, 34, 89};
        int i = m1(arr, 34);
        System.out.println("i = " + i);

    }

    private static int m1(int[] arr, int value) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == value) {
                return i;
            }
        }
        return -1;
    }
}
