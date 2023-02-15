package com.icboluo.leetcode.子串子序列;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author icboluo
 * @since 2023-02-15 23:46
 */
class N2405_字符串的最优分割 {
    /**
     * 字符串的最优分割
     * 将字符串分成多份，每一份字符都不重复，返回最小子串数
     *
     * @param s
     * @return
     */
    public int partitionString(String s) {
        Set<Character> set = new HashSet<>();
        int res = 1;
        for (int i = 0; i < s.length(); i++) {
            if (set.contains(s.charAt(i))) {
                res++;
                set.clear();
                set.add(s.charAt(i));
            } else {
                set.add(s.charAt(i));
            }
        }
        return res;
    }

    /**
     * 0763 分区标签
     * 将字符串分成多份，使每个字母只出现在一个区间里面
     * 怎么使每个字母只出现在一个区间里面呢，我们需要知道字母出现的第一个索引（并非很重要，和最后一个索引
     *
     * @param s
     * @return
     */
    public List<Integer> partitionLabels(String s) {
        Map<Character, Integer> eleIdxMap = IntStream.range(0, s.length())
                .boxed()
                .collect(Collectors.toMap(s::charAt, i -> i, (k1, k2) -> k2, HashMap::new));
        List<Integer> res = new ArrayList<>();
        int max = 0;
        int pre = -1;
        for (int i = 0; i < s.length(); i++) {
            char cur = s.charAt(i);
            // 更新当前区间内需要达到的最大索引
            max = Math.max(max, eleIdxMap.get(cur));
            // 如果当前索引就是最大索引，区间截断
            if (max == i) {
                res.add(max - pre);
                pre = i;
            }
        }
        return res;
    }

    /**
     * 0915 将数组划分为不相交的区间；分成2部分 使 all left<right;left & right notnull;求min left.length
     * 这个代码写出来是相当困难的 FIXME ERROR
     *
     * @param nums
     * @return
     */
    public int partitionDisjoint(int[] nums) {
// 如果左边区间存在一个数不满足条件，则需要左边区间继续扩大，寻找可行解
        int res = 0;
        int leftToCurMax = nums[0];
        int leftMax = nums[0];
        for (int i = 1; i < nums.length; i++) {
            // 如果当前值比较大，则是对的，需要更新左到现在的最大值
            if (nums[i] > leftMax) {
                leftToCurMax = Math.max(leftToCurMax, nums[i]);
            } else {
                res = i;
                leftMax = leftToCurMax;
            }
        }
        return res + 1;
    }

    // 2012 low
}
