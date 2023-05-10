package com.icboluo.leetcode.after_0000;

import java.util.HashMap;
import java.util.Map;

/**
 * @author icboluo
 * @since 2022-11-25 17:25
 */
class N0169_数组中超过一般的数字 {
    /**
     * 双层for
     *
     * @param nums
     * @return
     */
    public int majorityElement1(int[] nums) {
        // 需要加数组只有一个的判断
        if (nums.length == 1) {
            return nums[0];
        }
        for (int i = 0; i < nums.length - 1; i++) {
            // i出现的次数
            int count = 0;
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] == nums[j]) {
                    count++;
                }
            }
            if (++count > nums.length / 2) {
                return nums[i];
            }
        }
        return -1;
    }

    /**
     * 哈希表法
     *
     * @param nums
     * @return
     */
    public int majorityElement2(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int maxCount = 0;
        int maxNum = -1;
        for (int i = 0; i < nums.length; i++) {
            int count = map.getOrDefault(nums[i], 0) + 1;
            map.put(nums[i], count);
            if (count > maxCount) {
                maxCount = count;
                maxNum = nums[i];
            }
        }
        return maxNum;
    }

    /**
     * 摩尔投票法 有点难理解 TODO
     *
     * @param nums
     * @return
     */
    public int majorityElement3(int[] nums) {
        // 候选人
        int candidate = nums[0];
        int count = 1;
        for (int i = 1; i < nums.length; i++) {
            if (count == 0) {
                candidate = nums[i];
                count = 1;
            } else if (nums[i] == candidate) {
                count++;
            } else {
                count--;
            }
        }
        return candidate;
    }
}
