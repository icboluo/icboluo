package com.icboluo.leetcode.峰值低谷问题;

/**
 * @author icboluo
 * @since 2023-01-04 19:09
 */
class N0122_可以多次买入卖出 {
    /**
     * FIXME ERROR
     *
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        int sum = 0;
        int i = 0;
        while (i < prices.length - 1) {
            // 峰底，需要增加一个=号，表示非严格递增或者递减
            while (i < prices.length - 1 && prices[i + 1] <= prices[i]) {
                i++;
            }
            int a = prices[i];
            // 峰顶
            while (i < prices.length - 1 && prices[i + 1] >= prices[i]) {
                i++;
            }
            int b = prices[i];
            sum += b - a;
        }
        return sum;
    }
}
