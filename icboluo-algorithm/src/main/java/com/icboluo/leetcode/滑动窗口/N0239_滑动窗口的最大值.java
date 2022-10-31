package com.icboluo.leetcode.滑动窗口;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * @author icboluo
 * @since 2022-10-31 13:40
 */
class N0239_滑动窗口的最大值 {
    public static void main(String[] args) {
        N0239_滑动窗口的最大值 cla = new N0239_滑动窗口的最大值();
        int[] arr = {1, 3, -1, -3, 5, 3, 6, 7};
        int[] res = cla.m1(arr, 3);
        System.out.println(Arrays.toString(res));
    }

    /**
     * 双端队列 LinkedList
     * 队列头保留最大元素的值
     *
     * @param arr
     * @param n
     * @return
     */
    private int[] m1(int[] arr, int n) {
        int[] res = new int[arr.length - n + 1];
        // 队列里面存储的是索引
        Deque<Integer> deque = new LinkedList<>();
        for (int i = 0; i < arr.length; i++) {
            // 在尾部添加元素，并保证左边元素逗比尾部打，这样可以保证队列中的值递减（队列的值递减，首元素就是最大值；用i和n的关系来维护窗口大小
            while (!deque.isEmpty() && arr[deque.getLast()] < arr[i]) {
                deque.removeLast();
            }
            deque.addLast(i);
            // 如果窗口满了，移除最左元素
            if (deque.getFirst() == i - n) {
                deque.removeFirst();
            }
            // 如果可以记录最大值，开始记录
            if (i - n + 1 >= 0) {
                res[i - n + 1] = arr[deque.getFirst()];
            }
        }
        return res;
    }
}
