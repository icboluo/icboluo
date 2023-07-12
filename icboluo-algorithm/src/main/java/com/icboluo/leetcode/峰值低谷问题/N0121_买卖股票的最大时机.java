package com.icboluo.leetcode.峰值低谷问题;

/**
 * @author icboluo
 * @since 2023-01-04 20:39
 */
class N0121_买卖股票的最大时机 {
    /**
     * 只能买卖一次 TODO 好像有点看不懂了
     * 求左边最小，右边最大即可，这个方案采取的策略是跟进，每次出现降序的时候，将left指向right，所以left指针永远代表谷底
     *
     * @param arr
     * @return
     */
    public int maxProfit(int[] arr) {
        // 左指针就是最小指针
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
