package com.icboluo.leetcode.区间调度问题;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 贪心算法：贪心算法可以算是动态规划问题的一种特例
 * 贪心算法要求：每一步都做成一个局部最优的选择
 *
 * @author icboluo
 * @since 2023-03-16 23:53
 */
class N0435_无重叠区间 {
    @SuppressWarnings("all")
    public static void main(String[] args) {
        // 贪心算法
        var cla = new N0435_无重叠区间();
        int[][] arr1 = {// ----------------
                {1, 2},// ----------------
                {2, 3},// ----------------
                {3, 4},// ----------------
                {1, 3},// ----------------
        };
        int max = cla.eraseOverlapIntervals(arr1);
        System.out.println("max = " + max);
        int[][] arr2 = {// ----------
                {10, 16},// ------------------
                {2, 8},// ------------------
                {1, 6},// ------------------
                {7, 12},// ------------------
        };
        int min = cla.findMinArrowShots(arr2);
        System.out.println("min = " + min);
    }

    /**
     * 算出这些区间中最多有几个互不相交的区间，也就是：区间的最大不相交子集 FIXME ERROR
     * 从区间集合arr中选择一个区间x，这个x是在当前所有区间中结束最早的（end最小
     * 把所有与x区间相交的区间从区间集合arr中删除
     * 重复步骤1和2，知道arr为空为止。之前选出的哪些x就是最大不相交子集
     *
     * @param arr
     * @return
     */
    public int eraseOverlapIntervals(int[][] arr) {
        sortByEnd(arr);
        // 最少有一个区间
        int count = 1;
        int preEnd = arr[0][1];
        for (int i = 1; i < arr.length; i++) {
            int start = arr[i][0];
            int end = arr[i][1];
            if (start > preEnd) {
                count++;
                preEnd = end;
            }
        }
        return arr.length - count;
    }

    /**
     * N0452 用最少的箭头射爆气球
     *
     * @param arr
     * @return
     */
    public int findMinArrowShots(int[][] arr) {
        sortByEnd(arr);
        int count = 1;
        int preEnd = arr[0][1];
        for (int i = 1; i < arr.length; i++) {
            int start = arr[i][0];
            int end = arr[i][1];
            // 如果开始节点大于上一个的结束节点，说明是断开的，需要多放一箭
            if (start > preEnd) {
                count++;
                preEnd = end;
            }
        }
        return count;
    }

    private void sortByEnd(int[][] arr) {
        Arrays.sort(arr, Comparator.comparingInt(a -> a[1]));
    }
}
