package com.icboluo.leetcode.栈;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * @author icboluo
 * @since 2022-12-30 0:53
 */
class N1299_右侧下一个最大的元素 {
    /**
     * 求的是右侧中最大的元素，而不是比当前元素大的元素，不能用单调递增栈
     *
     * @param arr
     * @return
     */
    public int[] replaceElements1(int[] arr) {
        int[] res = new int[arr.length];
        Arrays.fill(res, -1);
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] < arr[i]) {
                int idx = stack.poll();
                res[idx] = arr[i];
            }
            stack.push(i);
        }
        return res;
    }

    /**
     * 倒着遍历赋值即可
     *
     * @param arr
     * @return
     */
    public int[] replaceElements(int[] arr) {
        int max = -1;
        for (int i = arr.length - 1; i >= 0; i--) {
            max = Math.max(arr[i], arr[i] = max);
        }
        return arr;
    }
}
