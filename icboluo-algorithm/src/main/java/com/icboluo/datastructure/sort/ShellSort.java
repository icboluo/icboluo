package com.icboluo.datastructure.sort;

import com.icboluo.util.RandomUtil;

import java.util.Arrays;

/**
 * 希尔排序,当最后的数较小，移动的次数就变多了，所以用希尔排序
 *
 * @author icboluo
 */
 class ShellSort {
    public static void main(String[] args) {
        int[] arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
        //m1(arr);
        int[] random = RandomUtil.getRandom(8000000);
        m3(random);
    }

    /**
     * 运用的是希尔排序的交换方法
     *
     * @param arr 要排序的数组
     */
    public static void m1(int[] arr) {
        int temp;
        //分10/2组
        for (int i = 5; i < arr.length; i++) {
            //遍历每组中所有的元素
            for (int j = i - 5; j >= 0; j -= 5) {
                if (arr[j] > arr[j + 5]) {
                    temp = arr[j];
                    arr[j] = arr[j + 5];
                    arr[j + 5] = temp;
                }
            }
        }
        System.out.println("第一次排序后数组为" + Arrays.toString(arr));

        //分5/2组
        for (int i = 2; i < arr.length; i++) {
            //遍历每组中所有的元素
            for (int j = i - 2; j >= 0; j -= 2) {
                if (arr[j] > arr[j + 2]) {
                    temp = arr[j];
                    arr[j] = arr[j + 2];
                    arr[j + 2] = temp;
                }
            }
        }
        System.out.println("第二次排序后数组为" + Arrays.toString(arr));

        //分2/2组
        for (int i = 1; i < arr.length; i++) {
            //遍历每组中所有的元素
            for (int j = i - 1; j >= 0; j -= 1) {
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        System.out.println("第二次排序后数组为" + Arrays.toString(arr));
    }

    public static void m2(int[] arr) {
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            int temp;
            //分10/2组
            for (int i = gap; i < arr.length; i++) {
                //遍历每组中所有的元素
                for (int j = i - gap; j >= 0; j -= gap) {
                    if (arr[j] > arr[j + gap]) {
                        temp = arr[j];
                        arr[j] = arr[j + gap];
                        arr[j + gap] = temp;
                    }
                }
            }
        }
    }

    /**
     * 对希尔排序的交换法进行优化->移位法，就是和插入差不多
     */
    public static void m3(int[] arr) {
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                int index = i;
                int temp = arr[i];
                if (arr[index] < arr[index - gap]) {
                    while (index - gap >= 0 && temp < arr[index - gap]) {
                        arr[index] = arr[index - gap];
                        index -= gap;
                    }
                    arr[index]=temp;
                }
            }

        }

    }


    public static void mm() {

    }
}
