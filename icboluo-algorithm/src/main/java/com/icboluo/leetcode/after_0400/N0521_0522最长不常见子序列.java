package com.icboluo.leetcode.after_0400;

import java.util.*;

/**
 * @author icboluo
 * @since 2022-11-07 21:14
 */
class N0521_0522最长不常见子序列 {
    /**
     * 题意：最长，字符串最长；不常见，只出现一次；子序列，这个是废话，a是b的子序列，b不是a的子序列，所以依然不是子序列，相当于equals
     *
     * @param a
     * @param b
     * @return
     */
    public int findLUSlength(String a, String b) {
        return a.equals(b) ? -1 : Math.max(a.length(), b.length());
    }

    // 0522 对字符串用长度排序，没有重复项，则最长的就是；有重复，可能下一个非重复的是，非重复的，但是也可能是大串的子序列，需要校验
    public int findLUSlength(String[] strs) {
        Arrays.sort(strs, (a, b) -> b.length() - a.length());

        Set<String> duplicate = getDuplicate(strs);
        for (int i = 0; i < strs.length; i++) {
            if (duplicate.contains(strs[i])) {
                continue;
            }
            if (i == 0) {
                return strs[0].length();
            }
            for (int j = 0; j < i; j++) {
                if (isSubsequence(strs[j], strs[i])) {
                    break;
                }
                // 如果没有找到，全部不是子序列
                if (j == i - 1) {
                    return strs[i].length();
                }
            }
        }
        return -1;
    }

    private boolean isSubsequence(String parent, String child) {
        int i = 0;
        int j = 0;
        while (i < parent.length() && j < child.length()) {
            if (parent.charAt(i) == child.charAt(j)) {
                j++;
            }
            i++;
        }
        return j == child.length();
    }

    private Set<String> getDuplicate(String[] arr) {
        HashSet<String> set = new HashSet<>();
        HashSet<String> duplicate = new HashSet<>();
        for (String s : arr) {
            if (set.contains(s)) {
                duplicate.add(s);
            }
            set.add(s);
        }
        return duplicate;
    }
}
