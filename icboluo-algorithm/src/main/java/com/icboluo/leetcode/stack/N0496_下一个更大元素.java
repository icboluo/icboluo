package com.icboluo.leetcode.stack;

import java.util.*;

/**
 * @author icboluo
 * @since 2022-12-15 22:32
 */
class N0496_下一个更大元素 {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums2.length; i++) {
            map.put(nums2[i], i);
        }
        // 数组2的下一个更大元素
        int[] arr = new int[nums2.length];
        Arrays.fill(arr, -1);
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < nums2.length; i++) {
            while (!stack.isEmpty() && nums2[stack.peek()] < nums2[i]) {
                Integer pop = stack.pop();
                arr[pop] = nums2[i];
            }
            stack.push(i);
        }
        int[] res = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            // 1元素在2里面的idx，再取2的next
            res[i] = arr[map.get(nums1[i])];
        }
        return res;
    }
}
