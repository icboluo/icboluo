package com.icboluo.leetcode.stack;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * @author icboluo
 * @since 2023-09-17 23:55
 */
class a_单调递增栈 {
    // 单调递增栈可以做什么？
    // 方法1.数组的前一个较大元素
    public int[] preLargeEle(int[] arr) {
        Deque<Integer> stack = new ArrayDeque<>();
        int[] res = new int[arr.length];
        Arrays.fill(res, -1);
        for (int i = 0; i < arr.length; i++) {
            // 栈中如果比较小，全部弹出即可，最终栈中的元素会比较大
            while (!stack.isEmpty() && arr[stack.peek()] < arr[i]) {
                stack.pop();
            }
            if (!stack.isEmpty()) {
                res[i] = arr[stack.peek()];
            }
            stack.push(i);
        }
        return res;
    }

    // 方法2.找到数组每个元素的下一个较大元素
    // 其实这个也可以用上面的方法倒序遍历数组
    // 单调递增栈是说，栈需要单调递减的，如果遇到一个较大的元素，将栈中较小的元素全部弹出来，这些较小元素的下一个更大元素便是当前元素
    public int[] nextLargeEle(int[] arr) {
        Deque<Integer> stack = new ArrayDeque<>();
        int[] res = new int[arr.length];
        Arrays.fill(res, -1);
        for (int i = 0; i < arr.length; i++) {
            // 栈比较小，当前元素比较大 res[小]=大
            while (!stack.isEmpty() && arr[stack.peek()] < arr[i]) {
                int idx = stack.pop();
                res[idx] = i;
            }
            stack.push(i);
        }
        return res;
    }
}
