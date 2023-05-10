package com.icboluo.leetcode.stack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author icboluo
 * @since 2023-01-07 16:21
 */
class N1475_商品折扣的最终价格 {
    /**
     * j>i && arr[j]<arr[i]
     * 其实也就是下一个较小元素，单调递增栈
     *
     * @param prices
     * @return
     */
    public int[] finalPrices(int[] prices) {
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < prices.length; i++) {
            while (!stack.isEmpty() && prices[stack.peek()] >= prices[i]) {
                Integer idx = stack.pop();
                prices[idx] -= prices[i];
            }
            stack.push(i);
        }
        return prices;
    }
}
