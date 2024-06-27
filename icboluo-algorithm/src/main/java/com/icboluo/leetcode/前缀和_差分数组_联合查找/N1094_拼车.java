package com.icboluo.leetcode.前缀和_差分数组_联合查找;

/**
 * @author icboluo
 * @since 2023-11-06 3:16
 */
class N1094_拼车 {
    public static void main(String[] args) {
        N1094_拼车 cla = new N1094_拼车();
        int[][] arr1 = {{2, 1, 5}, {3, 3, 7}};
        int[][] arr2 = {{2, 1, 5}, {3, 3, 7}};
        System.out.println(cla.carPooling(arr1, 4));
        System.out.println(cla.carPooling(arr2, 5));
    }

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
            // 这个是左闭右开区间，在右边界，乘客已经下车了
            diff.increment(trip[1], trip[2] - 1, trip[0]);
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
