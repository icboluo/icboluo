package com.icboluo.datastructure.sort;

import com.icboluo.util.RandomUtil;

import java.util.Arrays;

/**
 * 快速排序,用递归对冒泡排序进行改进
 *
 * @author lp
 */
 class QuickSort {

    public static void main(String[] args) {
        int[] arr = {-9, 78, 0, 23, -567, 70};
        m1(arr,0,arr.length-1);
        int[] random = RandomUtil.getRandom(8000000);
        m1(random, 0, random.length - 1);
        System.out.println(Arrays.toString(arr));
    }

    /**
     *
     * @param arr 排序数组
     * @param left 左下标
     * @param right 右下标
     */
    public static void m1(int[] arr, int left, int right) {
        int l = left;
        int r = right;
        //中轴
        int pivot = arr[(right + left) / 2];
        int temp;
        while (l < r) {
            while (arr[l] < pivot) {
                l++;
            }
            while (arr[r] > pivot) {
                r--;
            }
            //左边索引大于右边，不可能事件，等于代表排序完成（偶数情况）
            if (l >= r) {
                break;
            }
            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;
            //处理有数据相等的情况
            if (arr[l] == pivot) {
                r--;
            }
            if (arr[r] == pivot) {
                l++;
            }
        }
        if (l == r) {
            l++;
            r--;
        }

        if (left < r) {
            m1(arr, left, r);
        }
        if (right > l) {
            m1(arr,l,right);
        }
    }
}
