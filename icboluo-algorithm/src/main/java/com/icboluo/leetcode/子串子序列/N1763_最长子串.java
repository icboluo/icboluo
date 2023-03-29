package com.icboluo.leetcode.子串子序列;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author icboluo
 * @since 2023-03-29 23:06
 */
class N1763_最长子串 {
    /**
     * 要求窗口内每个字母既有大写右移小写
     * 这个问题没办法用滑动窗口来做，左指针压根不知道应不应该移动，右指针自己移动就可以求得最优解了
     *
     * @param s
     * @return
     */
    public String longestNiceSubstring1(String s) {
        int l = 0;
        int r = 0;
        int[] arrBig = new int[26];
        int[] arrSmall = new int[26];
        int notMatch = 0;
        while (r < s.length()) {
            char right = s.charAt(r++);
            int i;
            if (right >= 'a' && right <= 'z') {
                i = right - 'a';
                arrSmall[i]++;
            } else {
                i = right - 'A';
                arrBig[i]++;
            }
            if (arrSmall[i] == arrBig[i]) {
                notMatch--;
            }
        }
        return "";
    }

    /**
     * 分治：我们很难确定左右指针的移动，可以尝试使用切割的方式，单指针将不合法的地方切去即可解决
     *
     * @param s
     * @return
     */
    public String longestNiceSubstring2(String s) {
        Set<Character> set = IntStream.range(0, s.length()).mapToObj(s::charAt).collect(Collectors.toSet());
        for (int i = 0; i < s.length(); i++) {
            if (set.contains(Character.toLowerCase(s.charAt(i))) && set.contains(Character.toUpperCase(s.charAt(i)))) {
                continue;
            }
            String sub1 = longestNiceSubstring2(s.substring(0, i));
            String sub2 = longestNiceSubstring2(s.substring(i + 1));
            if (sub1.length() >= sub2.length()) {
                return sub1;
            }
            return sub2;
        }
        return s;
    }

    // TODO 2421 hard
}
