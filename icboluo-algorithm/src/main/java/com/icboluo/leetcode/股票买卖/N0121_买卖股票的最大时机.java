package com.icboluo.leetcode.股票买卖;

/**
 * @author icboluo
 * @since 2023-01-04 20:39
 */
class N0121_买卖股票的最大时机 {
    /**
     * 只能买卖一次 TODO 好像有点看不懂了
     * 求左边最小，右边最大即可
     *
     * @param arr
     * @return
     */
    public int maxProfit(int[] arr) {
        int left = 0;
        int right = 1;
        int max = 0;
        while (right < arr.length) {
            if (arr[left] < arr[right]) {
                max = Math.max(max, arr[right] - arr[left]);
            } else {
                left = right;
            }
            right++;
        }
        return max;
    }
}
