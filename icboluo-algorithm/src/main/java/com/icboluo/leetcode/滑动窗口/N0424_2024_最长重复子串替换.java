package com.icboluo.leetcode.滑动窗口;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeMap;

/**
 * @author icboluo
 * @since 2023-01-15 17:48
 */
class N0424_2024_最长重复子串替换 {
    /**
     * 可以把一个字母改为另一个字母，求长度
     * 不能简简单单的只判断pre 和cur 是否相等，这样会使问题难以追溯；
     * 我们无法固定一个元素，去使所有的值变成这个元素，这个是困难的，所以只能求子串的长度
     * 这个题的难点是建模，我们只能泛泛的统计区间内的值做窗口移动
     *
     * @param s
     * @param k
     * @return
     */
    public int characterReplacement(String s, int k) {
        int l = 0, r = 0;
        // TreeMap 的错误使用，不能根据val排序，这里使用是没有必要的
        TreeMap<Character, Integer> eleCountWinMap = new TreeMap<>();
        int maxCount = 0;
        while (r < s.length()) {
            char right = s.charAt(r++);
            eleCountWinMap.put(right, eleCountWinMap.getOrDefault(right, 0) + 1);
            // 我们可以缩减到原来的max值即可
            // 不合法的时候；对于此块我们的判断有些多余，可以加速，因为求的是最大值，我们没有必要去不断缩减直到合法
            while (r - l > maxCount
                    && r - l - eleCountWinMap.values().stream().mapToInt(Integer::intValue).max().getAsInt() > k) {
                char left = s.charAt(l++);
                eleCountWinMap.put(left, eleCountWinMap.get(left) - 1);
            }
            maxCount = Math.max(maxCount, r - l);
        }
        return maxCount;
    }

    /**
     * 2024 最大化考试的混乱
     * 把一个字母改为另一个字母，求相同长度
     *
     * @param answerKey 只有 TF
     * @param k         修改次数
     * @return
     */
    public int maxConsecutiveAnswers(String answerKey, int k) {
        int l = 0, r = 0;
        TreeMap<Character, Integer> eleCountWinMap = new TreeMap<>();
        int maxCount = 0;
        while (r < answerKey.length()) {
            char right = answerKey.charAt(r++);
            eleCountWinMap.put(right, eleCountWinMap.getOrDefault(right, 0) + 1);
            while (r - l > maxCount
                    && r - l - eleCountWinMap.values().stream().mapToInt(Integer::intValue).max().getAsInt() > k) {
                char left = answerKey.charAt(l++);
                eleCountWinMap.put(left, eleCountWinMap.get(left) - 1);
            }
            maxCount = Math.max(maxCount, r - l);
        }
        return maxCount;
    }

    // 1482 制作m束花的最少天数 TODO 二进制搜索/二分查找
    // 2401 最长的nice子数组 TODO 按位与什么的

    /**
     * N2461_长度为k的不同子数组的最大和
     * 2个条件，长度为k，每个元素均不同
     * 简单分析：长度我们可以由r-l获得，每个元素均不同我们可以由win获得，不过时间复杂度为n
     * 我们可以保证每个元素均不同，然后改变长度，这样效率比较高
     *
     * @param nums
     * @param k
     * @return
     */
    public long maximumSubarraySum(int[] nums, int k) {
        int l = 0, r = 0;
        Set<Integer> win = new HashSet<>();
        // 加速
        long sum = 0;
        long max = 0;
        while (r < nums.length) {
            int right = nums[r++];
            // 这里的第二个条件也是非常重要的，容易忽视，我们不要长度溢出
            while (win.contains(right) || win.size() == k) {
                sum -= nums[l];
                win.remove(nums[l++]);
            }
            win.add(right);
            sum += right;
            if (win.size() == k) {
                // 避免这里sum再次运算
                max = Math.max(max, sum);
            }
        }
        return max;
    }
}
