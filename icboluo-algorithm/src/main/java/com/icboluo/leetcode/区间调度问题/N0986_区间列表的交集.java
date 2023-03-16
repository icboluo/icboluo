package com.icboluo.leetcode.区间调度问题;

import com.icboluo.util.ArrayHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * @author icboluo
 * @since 2023-03-16 22:52
 */
class N0986_区间列表的交集 {
    @SuppressWarnings("all")
    public static void main(String[] args) {
        var cla = new N0986_区间列表的交集();
        int[][] arr1 = {
                {0, 2},// ------------------
                {5, 10},// ------------------
                {13, 23},// ------------------
                {24, 25},// ------------------
        };

        int[][] arr2 = {
                {1, 5},// ------------------
                {8, 12},// ------------------
                {15, 24},// ------------------
                {25, 26},// ------------------
        };

        int[][] res = cla.intervalIntersection(arr1, arr2);
        ArrayHelper.print(res);
    }

    public int[][] intervalIntersection(int[][] arr1, int[][] arr2) {
        int left = 0;
        int right = 0;
        List<int[]> res = new ArrayList<>();
        while (left < arr1.length && right < arr2.length) {
            int start1 = arr1[left][0];
            int end1 = arr1[left][1];
            int start2 = arr2[right][0];
            int end2 = arr2[right][1];
            if (start1 > end2 || start2 > end1) {
                // 没有交集不做处理
            }else{
                res.add(new int[]{Math.max(start1, start2), Math.min(end1, end2)});
            }
            if (end1 < end2) {
                left++;
            }else{
                right++;
            }
        }
        return res.toArray(int[][]::new);
    }
}
