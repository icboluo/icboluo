package com.icboluo.leetcode.after_0800;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * @author icboluo
 * @since 2022-11-22 22:30
 */
class N0905_0922根据奇偶排序 {
    // 747 数组最大元素是其他元素的至少2倍 TODO ERROR
    public int dominantIndex(int[] nums) {
        int max = -1;
        int second = -1;
        int idx = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > max) {
                second = max;
                max = nums[i];
                idx = i;
            } else if (nums[i] > second) {
                second = nums[i];
            }
        }
        return max > second * 2 ? idx : -1;
    }

    // 905
    public int[] sortArrayByParity(int[] nums) {
        int left = 0;
        int right = 0;
        while (right < nums.length) {
            if (nums[right] % 2 == 0) {
                int temp = nums[right];
                nums[right] = nums[left];
                nums[left] = temp;
                left++;
            }
            right++;
        }
        return nums;
    }

    // 0922 TODO ERROR
    public int[] sortArrayByParityII(int[] nums) {
        int[] arr = new int[nums.length];
        int even = 0;
        int odd = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i % 2 == 0) {
                while (nums[even] != 0) {
                    even++;
                }
                arr[i] = nums[even];
            } else {
                while (nums[odd] != 1) {
                    odd++;
                }
                arr[i] = nums[odd];
            }
        }
        return arr;
    }

    // 1346 数组中存在一个元素是一个元素的2倍 TODO ERROR
    public boolean checkIfExist(int[] arr) {
        Set<Integer> set = new HashSet<>();
        for (int num : arr) {
            if (set.contains(num * 2)) {
                return true;
            }
            set.add(num * 2);
        }
        return false;
    }

    // 2149 按符号重新排列数组元素 TODO ERROR
    public int[] rearrangeArray(int[] nums) {
        int even = 0;
        int odd = 1;
        int[] arr = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] % 2 == 0) {
                arr[even] = nums[i];
                even += 2;
            } else {
                arr[odd] = nums[i];
                odd += 2;
            }
        }
        return arr;
    }

    // 2154 将目标值不断乘2并在数组中找到
    public int findFinalValue(int[] nums, int original) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        while (set.contains(original)) {
            original *= 2;
        }
        return original;
    }

    // 2164
    public int[] sortEvenOdd(int[] nums) {
        PriorityQueue<Integer> incr = new PriorityQueue<>();
        PriorityQueue<Integer> decr = new PriorityQueue<>((a, b) -> b - a);
        for (int i = 0; i < nums.length; i++) {
            if (i % 2 == 0) {
                incr.add(nums[i]);
            } else {
                decr.add(nums[i]);
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (i % 2 == 0) {
                nums[i] = incr.poll();
            } else {
                nums[i] = decr.poll();
            }
        }
        return nums;
    }

    // 2231 奇数位或偶数位交换后的最大值 TODO ERROR
    public int largestInteger(int num) {
        String str = String.valueOf(num);
        PriorityQueue<Character> even = new PriorityQueue<>((a, b) -> b - a);
        PriorityQueue<Character> odd = new PriorityQueue<>((a, b) -> b - a);
        for (int i = 0; i < str.length(); i++) {
            if (i % 2 == 0) {
                even.add(str.charAt(i));
            } else {
                odd.add(str.charAt(i));
            }
        }
        String ans = "";
        for (int i = 0; i < str.length(); i++) {
            ans += i % 2 == 0 ? even.poll() : odd.poll();
        }
        return Integer.parseInt(ans);
    }
}
