package com.icboluo.leetcode.after_1700;

/**
 * @author icboluo
 * @since 2023-03-23 23:38
 */
class N1929_数组的链接重复2次 {
    /**
     * 数组重复2次
     *
     * @param nums
     * @return
     */
    public int[] getConcatenation(int[] nums) {
        int[] arr = new int[nums.length * 2];
        for (int i = 0; i < nums.length; i++) {
            arr[i] = nums[i];
            arr[nums.length + i] = nums[i];
        }
        return arr;
    }
}
