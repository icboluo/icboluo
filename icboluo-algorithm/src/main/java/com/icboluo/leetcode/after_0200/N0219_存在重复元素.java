package com.icboluo.leetcode.after_0200;

import java.util.PriorityQueue;

/**
 * @author icboluo
 * @since 2022-10-27 13:22
 */
class N0219_存在重复元素 {
    public static void main(String[] args) {
        N0219_存在重复元素 cla = new N0219_存在重复元素();
        int[] arr = {1, 2, 3, 1};
        boolean ans = cla.isExist(arr, 3);
        System.out.println("ans = " + ans);
    }

    /**
     * 滑动窗口
     *
     * @param arr
     * @param n
     * @return
     */
    private boolean isExist(int[] arr, int n) {
        // 没必要优先级队列，随便使用一个LinkedList queue就可以了
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < arr.length; i++) {
            if (pq.contains(arr[i])) {
                return true;
            }
            pq.offer(arr[i]);
            if (pq.size() == n + 1) {
                pq.poll();
            }
        }
        return false;
    }
}
