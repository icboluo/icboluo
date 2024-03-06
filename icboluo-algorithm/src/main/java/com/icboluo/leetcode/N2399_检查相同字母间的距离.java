package com.icboluo.leetcode;

/**
 * @author icboluo
 * @since 2024-02-26 22:42
 */
class N2399_检查相同字母间的距离 {
    // 检查相同字母之间的距离
    // 方案1：求2个字母的距离做成数组，判断是否equals，不够快
    public boolean checkDistances(String s, int[] distance) {
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            int dist = distance[ch - 'a'];
            // 方案2：当前到下一个字符出现的距离为dist
            if (i + dist + 1 >= s.length() || s.charAt(i + dist + 1) != ch) {
                return false;
            }
            distance[ch - 'a'] = -1;
        }
        return true;
    }

    // 这个代码是真的完全看不懂
    public static boolean checkDistances3(String s, int[] distance) {
        // 第二个出现的位置
        int[] secondArr = new int[26];
        for (int i = 0; i < s.length(); ++i) {
            // 数组中的位置
            int arrIdx = s.charAt(i) - 'a';
            // 如果 距离不等于
            if (secondArr[arrIdx] != 0 && distance[arrIdx] != i - secondArr[arrIdx]) {
                return false;
            }
            secondArr[arrIdx] = i + 1;
        }
        return true;
    }
}
