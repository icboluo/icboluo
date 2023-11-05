package com.icboluo.leetcode.前缀和_差分数组;

/**
 * @author icboluo
 * @since 2023-11-06 3:16
 */
public class N1094_拼车 {
    /**
     * 能否一次拉完
     *
     * @param trips    [[2,1,5],[3,3,7]] num start end
     * @param capacity 4 载客量
     * @return
     */
    public boolean carPooling(int[][] trips, int capacity) {
        // 对每个节点进行统计，判断最终节点的值；因为在一个节点上，上车下车汇总到一起便是这个节点的总人数
        int[] arr = new int[1001];
        DifferenceArr diff = new DifferenceArr(arr);
        for (int[] trip : trips) {
            diff.increment(trip[1], trip[2], trip[0]);
        }
        int[] res = diff.restore();
        for (int re : res) {
            if (capacity < re) {
                return false;
            }
        }
        return true;
    }
}
