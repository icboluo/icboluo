package com.icboluo.datastructure.sort;

import com.icboluo.util.DateHelper;
import com.icboluo.util.RandomHelper;

import java.util.Arrays;

/**
 * 插入排序
 *
 * @author icboluo
 */
class InsertSort {
    public static void main(String[] args) {
        int[] arr = {101, 34, 119, 1};
        //m1(arr);
        m2(arr);
        long cur1 = System.currentTimeMillis();
        m2(RandomHelper.getRandom(80000));
        long cur2 = System.currentTimeMillis();
        DateHelper.parseTimeStampToSecond(cur2 - cur1);
    }

    private static void m2(int[] arr) {
        //定义待插入的数
        int temp;
        int index;
        for (int i = 1; i < arr.length; i++) {
            //定义待插入的数
            temp = arr[i];
            index = i - 1;
            //给待插入的数找合适的位置
            while (index >= 0 && temp < arr[index]) {
                arr[index + 1] = arr[index];
                index--;
            }
            if (index + 1 != i) {
                arr[index + 1] = temp;
            }
       /*     System.out.println("第" + i + "轮后");
            System.out.println(Arrays.toString(arr));*/
        }
    }

    private static void m1(int[] arr) {
//定义待插入的数
        int temp = arr[1];
        int index = 0;
        //给待插入的数找合适的位置
        while (index >= 0 && temp < arr[index]) {
            arr[index + 1] = arr[index];
            index--;
        }
        arr[index + 1] = temp;
        System.out.println("第一轮后");
        System.out.println(Arrays.toString(arr));

        //定义待插入的数
        temp = arr[2];
        index = 1;
        //给待插入的数找合适的位置
        while (index >= 0 && temp < arr[index]) {
            arr[index + 1] = arr[index];
            index--;
        }
        arr[index + 1] = temp;
        System.out.println("第二轮后");
        System.out.println(Arrays.toString(arr));


        //定义待插入的数
        temp = arr[3];
        index = 2;
        //给待插入的数找合适的位置
        while (index >= 0 && temp < arr[index]) {
            arr[index + 1] = arr[index];
            index--;
        }
        arr[index + 1] = temp;
        System.out.println("第二轮后");
        System.out.println(Arrays.toString(arr));
    }
}
