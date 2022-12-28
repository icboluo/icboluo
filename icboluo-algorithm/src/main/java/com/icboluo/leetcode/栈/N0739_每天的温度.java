package com.icboluo.leetcode.栈;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author icboluo
 * @since 2022-12-15 22:46
 */
class N0739_每天的温度 {
    public int[] dailyTemperatures(int[] temperatures) {
        Deque<Integer> stack = new ArrayDeque<>();
        int[] res = new int[temperatures.length];
        for (int i = 0; i < temperatures.length; i++) {
            // 这个题存在递增递减性质，所以可以多次pop，仅一次push
            // 如果当前温度比较高
            while (!stack.isEmpty() && temperatures[stack.peek()] < temperatures[i]) {
                Integer pop = stack.pop();
                res[pop] = i - pop;
            }
            stack.push(i);
        }
        return res;
    }
}
