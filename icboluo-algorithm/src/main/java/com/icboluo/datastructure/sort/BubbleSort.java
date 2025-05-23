package com.icboluo.datastructure.sort;

import com.icboluo.util.RandomUtil;
import com.icboluo.util.TimeRecord;

import java.util.Arrays;

/**
 * 冒泡排序：相邻2个元素依次比较，将最大的数移动到最后，每次循环排好一个元素
 *
 * @author icboluo
 */
class BubbleSort {
    public static void main(String[] args) {
        TimeRecord.start();
        int[] arr = {3, 9, -1, 10, -2};
        // m1(arr);
        m2(arr);
        m2(RandomUtil.getRandom(4000));
        TimeRecord.recordMsg("第二次排序");
        m2(RandomUtil.getRandom(4000));
        TimeRecord.build();
    }

    private static void m2(int[] arr) {
        int temp;
        for (int i = 0; i < arr.length - 1; i++) {
            boolean flag = false;
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    flag = true;
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
            //如果一次交换都没有发生
            if (!flag) {
                break;
            }
        }
    }

    private static void m1(int[] arr) {
        int temp;
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                temp = arr[i];
                arr[i] = arr[i + 1];
                arr[i + 1] = temp;
            }
        }
        System.out.println("第一趟排序后的数组为：");
        System.out.println(Arrays.toString(arr));
        for (int i = 0; i < arr.length - 1 - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                temp = arr[i];
                arr[i] = arr[i + 1];
                arr[i + 1] = temp;
            }
        }
        System.out.println("第二趟排序后的数组为：");
        System.out.println(Arrays.toString(arr));
        for (int i = 0; i < arr.length - 1 - 2; i++) {
            if (arr[i] > arr[i + 1]) {
                temp = arr[i];
                arr[i] = arr[i + 1];
                arr[i + 1] = temp;
            }
        }
        System.out.println("第三趟排序后的数组为：");
        System.out.println(Arrays.toString(arr));
        for (int i = 0; i < arr.length - 1 - 3; i++) {
            if (arr[i] > arr[i + 1]) {
                temp = arr[i];
                arr[i] = arr[i + 1];
                arr[i + 1] = temp;
            }
        }
        System.out.println("第四趟排序后的数组为：");
        System.out.println(Arrays.toString(arr));
    }


}
