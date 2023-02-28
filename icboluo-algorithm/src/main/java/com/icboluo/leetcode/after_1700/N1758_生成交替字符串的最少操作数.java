package com.icboluo.leetcode.after_1700;

/**
 * @author icboluo
 * @since 2023-02-21 21:23
 */
class N1758_生成交替字符串的最少操作数 {
    // 把011110这样的字符串修改为交替字符串的最小变化数
    // 可以暴力枚举啊，只有2种情况 101010...，010101...
    // 这2种情况的结果是互补的，相加之和为s.length
    public int minOperations(String s) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (i % 2 != (s.charAt(i) - '0')) {
                count++;
            }
        }
        return Math.min(count, s.length() - count);
    }
}
