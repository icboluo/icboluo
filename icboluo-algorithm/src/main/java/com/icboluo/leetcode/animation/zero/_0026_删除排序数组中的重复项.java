package com.icboluo.leetcode.animation.zero;

/**
 * @author icboluo
 * @date 2020-10-09 21:29
 */
public class _0026_删除排序数组中的重复项 {
    public static void main(String[] args) {
        int[] arr = {1, 1, 2};
        int[] res = m1(arr);
    }

    /**
     * 双指针：快慢指针
     * 快慢指针的差就是res数组的大小
     * 如果元素相等，quick++，else slow、quick ++，并使slowEle=quickEle
     * @param arr
     * @return
     */
    private static int[] m1(int[] arr) {
        return new int[2];
    }
}
