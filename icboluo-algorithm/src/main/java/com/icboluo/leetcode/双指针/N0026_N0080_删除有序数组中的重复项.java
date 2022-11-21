package com.icboluo.leetcode.双指针;

/**
 * @author icboluo
 * @since 2022-06-29 0:15
 */
class N0026_N0080_删除有序数组中的重复项 {
    /**
     * N0026 删除只保留一个
     * 双指针：快慢指针
     * 快慢指针的差就是res数组的大小
     * 如果元素相等，quick++，else slow、quick ++，并使slowEle=quickEle
     *
     * @param nums 待处理数组
     * @return 处理后的数组
     */
    public int removeDuplicates1(int[] nums) {
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

    /**
     * N0080 删除只保留2个
     *
     * @param nums 待处理数组
     * @return 处理后的数组
     */
    public int removeDuplicates2(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int slow = 0;
        int fast = 0;
        int count = 0;
        while (fast < nums.length) {
            if (nums[fast] != nums[slow]) {
                slow++;
                nums[slow] = nums[fast];
                // 把小于2个的元素当着不同的
            } else if (slow < fast && count < 2) {
                slow++;
                nums[slow] = nums[fast];
            }
            count++;
            fast++;
            if (fast < nums.length && nums[fast] != nums[fast - 1]) {
                count = 0;
            }
        }
        return slow + 1;
    }
}
