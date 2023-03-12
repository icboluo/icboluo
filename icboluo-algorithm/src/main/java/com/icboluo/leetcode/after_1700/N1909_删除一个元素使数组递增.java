package com.icboluo.leetcode.after_1700;

/**
 * @author icboluo
 * @since 2023-03-13 2:12
 */
class N1909_删除一个元素使数组递增 {
    // 删除一个元素，使数组严格递增 2 3 1 2，对于这样的数据，31不合法，删除3，下一组对比是12判断为合法是不合理的
    // 这个题太难了
    public static boolean canBeIncreasing1(int[] nums) {
        boolean incr = true;
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] >= nums[i + 1]) {
                if (incr) {
                    incr = false;
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean canBeIncreasing(int[] nums) {
        boolean del = false;
        int preIdx = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[preIdx] < nums[i]) {
                preIdx = i;
                continue;
            }
            if (del) {
                return false;
            }
            del = true;
            // 此时cur<pre
            // 如果ppre>cur,则说明当前节点过小，需要删除当前节点
            if (i > 1 && nums[i - 2] > nums[i]) {
                preIdx = i - 1;
            }
            // 如果ppre<cur,则说明当前节点属于中间值，删除前驱节点即可
            if (i > 1 && nums[i - 2] < nums[i]) {
                preIdx = i;
            }
            // 个数不够，只能删除前驱节点
            if (i <= 1) {
                preIdx = i;
            }
        }
        return true;
    }
}
