package com.icboluo.leetcode.after_1700;

/**
 * @author icboluo
 * @since 2023-03-02 22:42
 */
class N2110_一只股票平稳下降的期数 {


    /**
     * 求递减区间，这似乎也是一种组合问题 FIXME ERROR
     *
     * @param prices 价格
     * @return 递减区间
     */
    public long getDescentPeriods(int[] prices) {
        long res = 0;
        int curCount = 1;
        if (prices.length > 0) {
            res += curCount;
        }
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] - prices[i - 1] > 0) {
                curCount++;
                res += curCount;
            } else {
                curCount = 1;
            }
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
