package com.icboluo.leetcode.after_1000;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @author icboluo
 * @since 2022-11-22 12:34
 */
class N2099_最大子序列 {
    public static void main(String[] args) {
        N2099_最大子序列 cla = new N2099_最大子序列();
        int[] arr = {63, -74, 61, -17, -55, -59, -10, 2, -60, -65};
        int[] ints = cla.maxSubsequence(arr, 9);
        System.out.println(Arrays.toString(ints));
    }

    public int[] maxSubsequence(int[] nums, int k) {
        // 此块代码非常小心：pq存储idx，根据nums[i]逆序；pq容器的大小是初始化大小（设置了没啥用，反正也不会变化）
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> nums[b] - nums[a]);
        for (int i = 0; i < nums.length; i++) {
            pq.add(i);
            // 可以在这里判断pq的长度，也可以在后面limit，都是可以的;好像不能这样写
        }
/*        // 这个顺序是对的
        while (!pq.isEmpty()) {
            System.out.println(pq.poll());
        }
        // 这个顺序是错的
        // 先limit前2个，再sort idx，这样就保证原始顺序不变，然后收集nums[i]
        return pq.stream().limit(k).sorted().mapToInt(i -> nums[i]).toArray();*/

        int[] res = new int[k];
        for (int i = 0; i < res.length; i++) {
            res[i] = pq.poll();
        }
        Arrays.sort(res);
        for (int i = 0; i < res.length; i++) {
            res[i] = nums[res[i]];
        }
        return res;
    }
}
