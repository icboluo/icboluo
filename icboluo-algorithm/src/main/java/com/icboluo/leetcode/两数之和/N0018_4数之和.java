package com.icboluo.leetcode.两数之和;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author icboluo
 * @since 2024-04-01 23:33
 */
class N0018_4数之和 {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        return kSum(nums, 0, 4, target);
    }

    private List<List<Integer>> kSum(int[] nums, int start, int k, long target) {
        List<List<Integer>> res = new ArrayList<>();
        if (k == 2) {
            twoSum(nums, start, target, res);
        } else {
            // 循环中的一次，会记录以 i 作为选中节点的所有结果值 ☆☆☆☆☆☆
            for (int i = start; i < nums.length - (k - 1); i++) {
                // 如果当前的元素=上一个元素，跳过
                if (i > start && nums[i] == nums[i - 1]) {
                    continue;
                }
                // 很好地递归代码
                List<List<Integer>> temp = kSum(nums, i + 1, k - 1, target - nums[i]);
                for (List<Integer> a : temp) {
                    a.add(0, nums[i]);
                }
                res.addAll(temp);
            }
        }
        return res;
    }

    private List<List<Integer>> twoSum(int[] nums, int start, long target, List<List<Integer>> res) {
        int left = start;
        int right = nums.length - 1;
        while (left < right) {
            long sum = nums[left] + nums[right];
            if (sum == target) {
                List<Integer> path = new ArrayList<>();
                path.add(nums[left]);
                path.add(nums[right]);
                res.add(path);
                while (left < right && nums[left] == nums[left + 1]) {
                    left++;
                }
                while (left < right && nums[right] == nums[right - 1]) {
                    right--;
                }
                left++;
                right--;
            } else if (sum < target) {
                left++;
            } else if (sum > target) {
                right--;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        // 将0015代码转移到2数之和
        var cla = new N0018_4数之和();
        int[] arr1 = {1, 0, -1, 0, -2, 2};
        int[] arr2 = {2, 2, 2, 2, 2};
        System.out.println(cla.fourSum(arr1, 0));
        System.out.println(cla.fourSum(arr2, 8));
    }
}
