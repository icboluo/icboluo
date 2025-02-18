package com.icboluo.datastructure.sort;

import com.icboluo.util.RandomUtil;

import java.util.Arrays;

/**
 * 插入排序：取第一个元素，认为已经排序，将新元素依次与已排序元素比较插入
 *
 * @author icboluo
 */
class InsertSort {
    public static void main(String[] args) {
        int[] arr = {101, 34, 119, 1};
        //m1(arr);
        m2(arr);
        m2(RandomUtil.getRandom(80000));
    }

    private static void m2(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            //定义待插入的数
            int temp = arr[i];
            int index = i - 1;
            //给待插入的数找合适的位置
            while (index >= 0 && temp < arr[index]) {
                arr[index + 1] = arr[index];
                index--;
            }
            // 如果待排序元素是最大的，则不重新赋值
            if (index + 1 != i) {
                arr[index + 1] = temp;
            }
       /*     System.out.println("第" + i + "轮后");
            System.out.println(Arrays.toString(arr));*/
        }
    }

    private static void m1(int[] arr) {
        // 待排序元素（新元素
        int temp = arr[1];
        // 已排序索引
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
