package com.icboluo.leetcode;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.TreeMap;

/**
 * @author icboluo
 * @since 2024-02-26 22:26
 */
class N1710_卡车上的最大单元数 {
    /**
     * 因为拿的个数是固定的，所以先拿最大的
     *
     * @param boxTypes
     * @param truckSize 拿的个数
     * @return
     */
    public int maximumUnits(int[][] boxTypes, int truckSize) {
        Arrays.sort(boxTypes, (a, b) -> b[1] - a[1]);
        int sum = 0;
        for (int[] boxType : boxTypes) {
            if (truckSize > boxType[0]) {
                sum += boxType[0] * boxType[1];
                truckSize -= boxType[0];
            } else {
                sum += truckSize * boxType[1];
                return sum;
            }
        }
        return sum;
    }

    // 计数排序：
    public int maximumUnits2(int[][] boxTypes, int truckSize) {
        TreeMap<Integer, Integer> weightCountMap = new TreeMap<>();
        for (int[] boxType : boxTypes) {
            weightCountMap.put(boxType[1], boxType[0]);
        }
        int sum = 0;
        for (Integer weight : weightCountMap.descendingKeySet()) {
            // 这里的写法和上面的写法是一致的
            int count = Math.min(weightCountMap.get(weight), truckSize);
            sum += count * weight;
            truckSize -= count;
            if (truckSize == 0) {
                return sum;
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        int[] arr = {
                1, 4, 9, 2, 5, 3, 7, 6, 22, 23, 15, 24, 0, 3,//------------------------------------
                4, 5, 2, 3, 5, 12, 1, 3, 4, 2, 1,// --------------------------------------
                3, 45, 1, 1
        };
        int[] ints = countSort(arr);
        System.out.println(Arrays.toString(ints));
    }

    /**
     * 计数排序
     *
     * @param arr
     * @return
     */
    private static int[] countSort(int[] arr) {
        // 1. 缩短长度，为了第二步服务，也可以直接用treeMap
        IntSummaryStatistics intSummaryStatistics = Arrays.stream(arr).summaryStatistics();
        int min = intSummaryStatistics.getMin();
        int max = intSummaryStatistics.getMax();
        // 2.建立元素、个数键值对,使用数组存在内存浪费
        int[] eleCountArr = new int[max - min + 1];
        for (int i = 0; i < arr.length; i++) {
            eleCountArr[arr[i] - min]++;
        }
        // 前缀和数组
        int[] preSumArr = new int[eleCountArr.length];
        preSumArr[0] = eleCountArr[0];
        for (int i = 1; i < eleCountArr.length; i++) {
            preSumArr[i] = preSumArr[i - 1] + eleCountArr[i];
        }
        int[] res = new int[arr.length];
        for (int i = arr.length - 1; i >= 0; i--) {
            int curIdx = arr[i] - min;
            // arr[i] 为最后一个元素，最后一个元素应该放的位置为该元素出现的位置的前缀和
            res[preSumArr[curIdx] - 1] = arr[i];
            preSumArr[curIdx]--;
        }
        return res;
    }
}
