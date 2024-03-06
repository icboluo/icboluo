package com.icboluo.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author icboluo
 * @since 2024-02-26 22:23
 */
class N0901_股票价格跨度 {
    List<Integer> list;

    List<Integer> dp;

    public N0901_股票价格跨度() {
        list = new ArrayList<>();
        dp = new ArrayList<>();
    }

    public int next(int price) {
        int count = 1;
        for (int i = list.size() - 1; i >= 0; ) {
            if (list.get(i) <= price) {
                count += dp.get(i);
                i -= dp.get(i);
            } else {
                break;
            }
        }

        dp.add(count);
        list.add(price);
        return count;
    }

    Stack<int[]> stack = new Stack<>();

    public int next2(int price) {
        int res = 1;
        while (!stack.isEmpty() && stack.peek()[0] < price) {
            int[] pop = stack.pop();
            // 这个+=代表着线段的拼接
            res += pop[1];
        }
        // 价格，结果
        stack.push(new int[]{price, res});
        return res;
    }
}
