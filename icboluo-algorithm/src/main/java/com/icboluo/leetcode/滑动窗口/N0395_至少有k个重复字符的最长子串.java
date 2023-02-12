package com.icboluo.leetcode.滑动窗口;

import java.util.HashMap;
import java.util.Map;

/**
 * @author icboluo
 * @since 2023-01-15 18:36
 */
class N0395_至少有k个重复字符的最长子串 {
    public static void main(String[] args) {
        var cla = new N0395_至少有k个重复字符的最长子串();
        System.out.println(cla.longestSubstring("bbaaacbd", 3));
    }

    /**
     * 每个字符出现的频率均大于k TODO 340 FIXME ERROR
     * 这个题有点难受的，因为我们没办法通过指针的运作完成：右指针可以右移，但是左指针右移的时机很难确定
     * 引入外层循环，求出元素个数为1-26的所有结果集（暴力解
     *
     * @param s
     * @param k
     * @return
     */
    public int longestSubstring(String s, int k) {
        int res = 0;
        for (int i = 0; i < 26; i++) {
            int temp = longestSubstringSub(s, k, i + 1);
            res = Math.max(res, temp);
        }
        return res;
    }

    /**
     * @param s
     * @param k
     * @param winNeedDistinctEleCount 窗口需要的不同字符数
     * @return
     */
    private int longestSubstringSub(String s, int k, int winNeedDistinctEleCount) {
        Map<Character, Integer> eleCountWinMap = new HashMap<>();
        int eleValidCount = 0;
        int res = 0;
        int l = 0, r = 0;
        while (r < s.length()) {
            char right = s.charAt(r++);
            eleCountWinMap.put(right, eleCountWinMap.getOrDefault(right, 0) + 1);
            if (eleCountWinMap.get(right) == k) {
                eleValidCount++;
            }
//            这里不应该使用元素有效个数来判断，左指针右移，起码要保证数据合法
//            while (eleValidCount > winNeedDistinctEleCount) {
            while (eleCountWinMap.size() > winNeedDistinctEleCount) {
                char left = s.charAt(l++);
                if (eleCountWinMap.get(left) == k) {
                    eleValidCount--;
                }
                eleCountWinMap.put(left, eleCountWinMap.get(left) - 1);
                eleCountWinMap.remove(left, 0);
            }
            if (eleValidCount == eleCountWinMap.size()) {
                res = Math.max(res, r - l);
            }
        }
        return res;
    }
}
