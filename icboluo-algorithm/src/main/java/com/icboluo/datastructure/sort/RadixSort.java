package com.icboluo.datastructure.sort;

import com.icboluo.util.RandomUtil;

import java.util.Arrays;

/**
 * 基数排序，这个类似于字符串比较大小，会耗费空间，效率比快速排序还要高
 * （稳定性排序法，顺序不变，2个相等，在前面的还在前面，后面的还在后面）以后桶的个数必须用k去定义，不准用ijk...
 *
 * @author icboluo
 * @since 2020/5/31 19:20
 */
class RadixSort {
    public static void main(String[] args) {
        int[] arr = {53, 3, 542, 748, 14, 214};
        //8千万*11个桶*4个字节/1024/1024/1024=3.3g
        int[] random = RandomUtil.getRandom(80000000);
        m2(random);
    }

    private static void m2(int[] arr) {
        int maxLength = getMaxLength(arr);
        for (int m = 0, n = 1; m < maxLength; m++, n *= 10) {
            int[][] bucket = new int[10][arr.length];
            int[] bucketElementCounts = new int[10];
            //个位数进行排序处理（放入桶）
            for (int i = 0; i < arr.length; i++) {
                int digitOfElement = arr[i] / n % 10;
                bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[i];
                bucketElementCounts[digitOfElement]++;
            }
            //取出桶
            int index = 0;
            for (int i = 0; i < bucketElementCounts.length; i++) {
                if (bucketElementCounts[i] != 0) {
                    for (int j = 0; j < bucketElementCounts[i]; j++) {
                        arr[index++] = bucket[i][j];
                    }
                }
                bucketElementCounts[i] = 0;
            }
        }
    }

    private static int getMaxLength(int[] arr) {
        int max = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        return (max + "").length();
    }

    private static void m1(int[] arr) {
        int[][] bucket = new int[10][arr.length];
        int[] bucketElementCounts = new int[10];
        //个位数进行排序处理（放入桶）
        for (int i = 0; i < arr.length; i++) {
            int digitOfElement = arr[i] % 10;
            bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[i];
            bucketElementCounts[digitOfElement]++;
        }
        //取出桶
        int index = 0;
        for (int i = 0; i < bucketElementCounts.length; i++) {
            if (bucketElementCounts[i] != 0) {
                for (int j = 0; j < bucketElementCounts[i]; j++) {
                    arr[index++] = bucket[i][j];
                }
            }
            bucketElementCounts[i] = 0;
        }
        System.out.println("个位数排序结果为" + Arrays.toString(arr));
        //十位数进行排序处理（放入桶）
        for (int i = 0; i < arr.length; i++) {
            int digitOfElement = arr[i] / 10 % 10;
            bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[i];
            bucketElementCounts[digitOfElement]++;
        }
        //取出桶
        index = 0;
        for (int i = 0; i < bucketElementCounts.length; i++) {
            if (bucketElementCounts[i] != 0) {
                for (int j = 0; j < bucketElementCounts[i]; j++) {
                    arr[index++] = bucket[i][j];
                }
            }
            //清空每一个桶
            bucketElementCounts[i] = 0;
        }
        System.out.println("十位数排序结果为" + Arrays.toString(arr));
        //百位数进行排序处理（放入桶）
        for (int i = 0; i < arr.length; i++) {
            int digitOfElement = arr[i] / 100 % 10;
            bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[i];
            bucketElementCounts[digitOfElement]++;
        }
        //取出桶
        index = 0;
        for (int i = 0; i < bucketElementCounts.length; i++) {
            if (bucketElementCounts[i] != 0) {
                for (int j = 0; j < bucketElementCounts[i]; j++) {
                    arr[index++] = bucket[i][j];
                }
            }
            bucketElementCounts[i] = 0;
        }
        System.out.println("百位数排序结果为" + Arrays.toString(arr));
    }
}
