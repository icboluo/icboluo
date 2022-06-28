package com.icboluo.leetcode.双指针;

/**
 * @author icboluo
 * @since 2022-06-29 0:15
 */
public class N0026_删除有序数组中的重复项 {
    public int removeDuplicates(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int slow = 0;
        int fast = 0;
        while (fast < nums.length) {
            if (nums[slow] != nums[fast]) {
                // slow里面存储的是前置fast元素，如果slow，fast不等于，则让下一个slow存储fast
                slow++;
                nums[slow] = nums[fast];
            }
            fast++;
        }
        return slow + 1;
    }
}
