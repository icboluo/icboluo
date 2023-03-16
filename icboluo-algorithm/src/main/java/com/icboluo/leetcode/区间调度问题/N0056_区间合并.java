package com.icboluo.leetcode.区间调度问题;

import com.icboluo.util.ArrayHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @author icboluo
 * @since 2023-03-16 23:01
 */
class N0056_区间合并 {
    @SuppressWarnings("all")
    public static void main(String[] args) {
        var cla = new N0056_区间合并();
        int[][] arr = {
                {1, 3},// ----------------------------
                {2, 6},// ----------------------------
                {8, 10},// ----------------------------
                {15, 18},// ----------------------------
        };
        int[][] res = cla.merge(arr);
        ArrayHelper.print(res);
    }

    public int[][] merge(int[][] arr) {
        sortByStart(arr);
        // 当前连续块开始索引
        int preStart = arr[0][0];
        // 前置节点结束索引
        int preEnd = arr[0][1];
        List<int[]> res = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            int[] curArr = arr[i];
            int curStart = curArr[0];
            int curEnd = curArr[1];
            // 如果相交（前置节点的结束索引大于当前节点的开始索引
            if (preEnd >= curStart) {
                preEnd = Math.max(preEnd, curEnd);
            } else {
                res.add(new int[]{preStart, preEnd});
                preEnd = curEnd;
                preStart = curStart;
            }
            if (i == arr.length - 1) {
                res.add(new int[]{preStart, preEnd});
            }
        }
        return res.toArray(int[][]::new);
    }

    private void sortByStart(int[][] arr) {
        Arrays.sort(arr, Comparator.comparingInt(a -> a[0]));
    }
}
