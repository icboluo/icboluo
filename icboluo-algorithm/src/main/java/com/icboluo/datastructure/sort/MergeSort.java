package com.icboluo.datastructure.sort;

import com.icboluo.util.RandomHelper;

/**
 * 归并排序
 *
 * @author icboluo
 */
 class MergeSort {
    public static void main(String[] args) {
        //int[] arr = {8, 4, 5, 7, 1, 3, 6, 2};
        int[] arr = RandomHelper.getRandom(8000000);
        int[] temp = new int[arr.length];
        mergeSort(arr,0,arr.length-1, temp);
        //System.out.println(Arrays.toString(arr));

    }

    public static void mergeSort(int[] arr, int left, int right, int[] temp) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(arr, left, mid, temp);
            mergeSort(arr, mid+1, right, temp);
            merge(arr,left,mid,right,temp);
        }
    }

    /**
     * 合并的方法
     *
     * @param arr   待排序的数组
     * @param left  左边有序序列的初始索引
     * @param mid   中间...
     * @param right 右边索引
     * @param temp  中转数组
     */
    public static void merge(int[] arr, int left, int mid, int right, int[] temp) {
        int i = left;
        //右边有序数列的初始索引
        int j = mid + 1;
        //指向temp数组的当前索引
        int t = 0;
        while (i <= mid && j <= right) {
            //如果左边的小于右边的，吧左边的放到temp中
            if (arr[i] <= arr[j]) {
                temp[t] = arr[i];
                t++;
                i++;
            } else {
                temp[t] = arr[j];
                t++;
                j++;
            }
        }
        //左边有剩余
        while (i <= mid) {
            temp[t] = arr[i];
            t++;
            i++;
        }
        while (j <= right) {
            temp[t] = arr[j];
            t++;
            j++;
        }
        t = 0;
        int tempLeft = left;
        while (tempLeft <= right) {
            arr[tempLeft] = temp[t];
            t++;
            tempLeft++;
        }
    }
    public void end() {

    }
}
