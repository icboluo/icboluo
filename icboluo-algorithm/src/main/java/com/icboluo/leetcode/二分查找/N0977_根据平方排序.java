package com.icboluo.leetcode.二分查找;

/**
 * @author icboluo
 * @since 2022-11-22 21:48
 */
class N0977_根据平方排序 {
    public int[] sortedSquares(int[] nums) {
        int[] arr = new int[nums.length];
        int l = 0;
        int r = nums.length - 1;
        int idx = nums.length - 1;
        // 等号表示能取到值
        while (l <= r) {
            int left = (int) Math.pow(nums[l], 2);
            int right = (int) Math.pow(nums[r], 2);
            if (left > right) {
                arr[idx--] = left;
                l++;
            } else {
                arr[idx--] = right;
                r--;
            }
        }
        return arr;
    }
}
