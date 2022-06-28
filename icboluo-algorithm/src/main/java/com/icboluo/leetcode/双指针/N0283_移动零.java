package com.icboluo.leetcode.双指针;

/**
 * @author icboluo
 * @since 2022-06-29 0:41
 */
public class N0283_移动零 {
    public void moveZeroes(int[] nums) {
        if (nums.length == 0) {
            return;
        }
        int p = removeZeroes(nums, 0);
        for (int i = p; i < nums.length; i++) {
            nums[i] = 0;
        }
    }

    private int removeZeroes(int[] arr, int val) {
        int slow = 0;
        int fast = 0;
        while (fast < arr.length) {
            if (arr[fast] != val) {
                arr[slow] = arr[fast];
                slow++;
            }
            fast++;
        }
        return slow;
    }
}
