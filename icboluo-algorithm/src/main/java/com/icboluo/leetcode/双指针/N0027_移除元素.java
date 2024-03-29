package com.icboluo.leetcode.双指针;

/**
 * @author icboluo
 * @since 2022-06-29 0:26
 */
class N0027_移除元素 {
    public int removeElement(int[] nums, int val) {
        if (nums.length == 0) {
            return 0;
        }
        int slow = 0;
        int fast = 0;
        while (fast < nums.length) {
            if (nums[fast] != val) {
                // slow存储的是下一个元素，需要正确的元素，如果fast合法，则需要把合法数据放进slow
                // 后++可以保证0-slow-1区间内是不包含不合法元素的，如果前++，则说明会保留1个
                nums[slow] = nums[fast];
                slow++;
            }
            fast++;
        }
        return slow;
    }
}
