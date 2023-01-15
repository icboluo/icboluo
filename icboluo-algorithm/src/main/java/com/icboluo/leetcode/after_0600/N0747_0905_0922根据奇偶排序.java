package com.icboluo.leetcode.after_0600;

import java.util.*;

/**
 * @author icboluo
 * @since 2022-11-22 22:30
 */
class N0747_0905_0922根据奇偶排序 {
    // 747 数组最大元素是其他元素的至少2倍
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
        // 这块需要加上一个等号
        return max >= second * 2 ? idx : -1;
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

    /**
     * 0922 根据奇偶排序
     * 里面奇数偶数各占一半，使当i为奇数的时候，arr[i]也为奇数
     *
     * @param nums
     * @return
     */
    public int[] sortArrayByParityII(int[] nums) {
        int[] arr = new int[nums.length];
        int even = 0;
        int odd = 0;
        // 这里采用的是以结果集为导向，每一个位置使用奇偶元素填充
        for (int i = 0; i < nums.length; i++) {
            if (i % 2 == 0) {
                // 如果当前不是偶数，一直加到偶数
                while (nums[even] % 2 != 0) {
                    even++;
                }
                // 这个偶数使用过了，应该++舍去
                arr[i] = nums[even++];
            } else {
                while (nums[odd] % 2 != 1) {
                    odd++;
                }
                arr[i] = nums[odd++];
            }
        }
        return arr;
    }

    // 1346 数组中存在一个元素是一个元素的2倍 ERROR
    public boolean checkIfExist1(int[] arr) {
        Set<Integer> set = new HashSet<>();
        for (int num : arr) {
            // 这样的写法set集合刚开始并不是全量数据，包含判断是不完全的
            if (set.contains(num * 2)) {
                return true;
            }
            set.add(num * 2);
        }
        return false;
    }

    // -10,12,-20,-8,15 负数没法处理 FIXME ERROR
    public boolean checkIfExist2(int[] arr) {
        // 我们期望，数组里面的值不断的变大，然后我们由大到小即可判断
        arr = Arrays.stream(arr).boxed()
                .sorted(Comparator.comparingInt(Math::abs))
                .mapToInt(Integer::intValue)
                .toArray();
        Set<Integer> set = new HashSet<>();
        for (int i = arr.length - 1; i >= 0; i--) {
            if (set.contains(arr[i] * 2)) {
                return true;
            }
            set.add(arr[i]);
        }
        return false;
    }

    /**
     * 2149 按符号重新排列数组元素
     * 重排数组，使相邻元素一个为正一个为负；正数开头
     *
     * @param nums
     * @return
     */
    public int[] rearrangeArray(int[] nums) {
        int even = 0;
        int odd = 1;
        int[] arr = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            // 这里采用的是将数组的每一个元素放到合适的位置
            if (nums[i] > 0) {
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

    /**
     * 2231 奇数位或偶数位交换后的最大值
     * 可以交换任意2个偶数或奇数数字，交换多次，返回交换后的最大值
     *
     * @param num
     * @return
     */
    public int largestInteger(int num) {
        String str = String.valueOf(num);
        PriorityQueue<Character> even = new PriorityQueue<>((a, b) -> b - a);
        PriorityQueue<Character> odd = new PriorityQueue<>((a, b) -> b - a);
        for (int i = 0; i < str.length(); i++) {
            if (Character.getNumericValue(str.charAt(i)) % 2 == 0) {
                even.add(str.charAt(i));
            } else {
                odd.add(str.charAt(i));
            }
        }
        String ans = "";
        for (int i = 0; i < str.length(); i++) {
            ans += Character.getNumericValue(str.charAt(i)) % 2 == 0 ? even.poll() : odd.poll();
        }
        return Integer.parseInt(ans);
    }
}
