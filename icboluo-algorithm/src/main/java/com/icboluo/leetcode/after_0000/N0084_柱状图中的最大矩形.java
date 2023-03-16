package com.icboluo.leetcode.after_0000;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author icboluo
 * @since 2023-03-17 0:19
 */
class N0084_柱状图中的最大矩形 {
    public static void main(String[] args) {
        var cla = new N0084_柱状图中的最大矩形();
        int res = cla.largestRectangleArea2(new int[]{2, 1, 5, 6, 2, 3});
        System.out.println("res = " + res);
    }

    /**
     * 暴力解法：求每一个子区间的面积 超时
     *
     * @param arr
     * @return
     */
    public int largestRectangleArea1(int[] arr) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            int minHigh = arr[i];
            for (int j = i; j < arr.length; j++) {
                minHigh = Math.min(minHigh, arr[j]);
                max = Math.max(max, minHigh * (j - i + 1));
            }
        }
        return max;
    }

    /**
     * 单调栈
     *
     * @param arr
     * @return
     */
    public int largestRectangleArea2(int[] arr) {
        Deque<Integer> stack = new ArrayDeque<>();
        int res = 0;
        // 每一次计算都是左边的矩形面积
        for (int i = 0; i <= arr.length; i++) {
            int curEle = i == arr.length ? -1 : arr[i];
            while (!stack.isEmpty() && arr[stack.peek()] >= curEle) {
                // 如果栈中的元素比当前元素大，也就是说前面比较高，那就消耗栈，直到栈中元素较小
                int high = arr[stack.poll()];
                int width = stack.isEmpty() ? i : i - stack.peek() - 1;
                res = Math.max(res, high * width);
            }
            stack.push(i);
        }
        return res;
    }
}
