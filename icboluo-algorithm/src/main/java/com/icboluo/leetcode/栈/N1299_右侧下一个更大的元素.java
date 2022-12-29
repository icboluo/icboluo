package com.icboluo.leetcode.栈;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * @author icboluo
 * @since 2022-12-30 0:53
 */
class N1299_右侧下一个更大的元素 {
    // FIXME ERROR
    public int[] replaceElements(int[] arr) {
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
}
