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
        mergeSort(arr, 0, arr.length - 1, temp);
        //System.out.println(Arrays.toString(arr));

    }

    /**
     * @param arr
     * @param left
     * @param right
     * @param temp  可以将temp抽取成全局变量，减少参数传递
     */
    public static void mergeSort(int[] arr, int left, int right, int[] temp) {
        // 也可以这样判断
/*        if (left == right) {
            return;
        }*/
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(arr, left, mid, temp);
            mergeSort(arr, mid + 1, right, temp);
            merge(arr, left, mid, right, temp);
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
                temp[t++] = arr[i++];
            } else {
                temp[t++] = arr[j++];
            }
        }
        //左边有剩余
        while (i <= mid) {
            temp[t++] = arr[i++];
        }
        while (j <= right) {
            temp[t++] = arr[j++];
        }
        t = 0;
        int tempLeft = left;
        while (tempLeft <= right) {
            arr[tempLeft] = temp[t];
            t++;
            tempLeft++;
        }
    }

    private static void merge2(int[] arr, int left, int mid, int right, int[] temp) {
        for (int i = left; i <= right; i++) {
            temp[i] = arr[i];
        }
        int i = left;
        int j = mid + 1;
        for (int k = left; k <= right; k++) {
            if (i == mid + 1) {
                arr[k] = temp[j++];
            } else if (j == right + 1) {
                arr[k] = temp[i++];
            } else if (temp[i] > temp[j]) {
                arr[k] = temp[j++];
            } else {
                arr[k] = temp[i++];
            }
        }
    }

    /**
     * N0088
     * 1.合并后排序
     * 2....
     * 3.双指针，从后向前比较 (也可以从前向后比较
     *
     * @param arr1
     * @param arr2
     * @return
     */
    public int[] merge(int[] arr1, int[] arr2) {
        int i = arr1.length - 1;
        int j = arr2.length - 1;
        int[] arr = new int[arr1.length + arr2.length];
        int k = arr1.length + arr2.length;
        while (i >= 0 && j >= 0) {
            if (arr1[i] > arr2[j]) {
                arr[k] = arr1[i--];
            } else {
                arr[k] = arr2[j--];
            }
            k--;
        }
        while (i >= 0) {
            arr[k--] = arr1[i--];
        }
        while (j >= 0) {
            arr[k--] = arr[j--];
        }
        return arr;
    }
}
