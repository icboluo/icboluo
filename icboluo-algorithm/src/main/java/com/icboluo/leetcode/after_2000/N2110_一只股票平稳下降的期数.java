package com.icboluo.leetcode.after_2000;

/**
 * @author icboluo
 * @since 2023-03-02 22:42
 */
class N2110_一只股票平稳下降的期数 {
    public static void main(String[] args) {
        var cla = new N2110_一只股票平稳下降的期数();
        System.out.println(cla.numSubarrayProductLessThanK(new int[]{1, 1, 1}, 1));
    }

    /**
     * 求递减区间，这似乎也是一种组合问题
     *
     * @param prices 价格
     * @return 递减区间
     */
    public long getDescentPeriods(int[] prices) {
        if (prices.length == 0) {
            return 0;
        }
        // 这个res的结果为修正值
        long res = 1;
        // 计数器记录当前元素，按1开始
        int count = 1;
        for (int i = 0; i < prices.length - 1; i++) {
            if (prices[i] - prices[i + 1] == 1) {
                count++;
            } else {
                count = 1;
            }
            res += count;
        }
        return res;
    }

    // TODO 2348

    /**
     * 0713 小于K的子数组积，组合问题，滑动窗口问题，特殊组合问题 TODO 2302 hard FIXME ERROR
     *
     * @param nums
     * @param k
     * @return
     */
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        if (k == 0) {
            return 0;
        }
        int res = 0;
        int l = 0;
        int r = 0;
        int win = 1;
        while (r < nums.length) {
            int right = nums[r++];
            win *= right;
            while (win >= k) {
                int left = nums[l++];
                win /= left;
            }
            res += r - l;
        }
        return res;
    }
}
