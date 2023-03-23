package com.icboluo.leetcode.after_1700;

/**
 * @author icboluo
 * @since 2023-03-23 23:22
 */
class N1974_组成目标单词的最小时间 {
    /**
     * 26个字母排列好，初始指针指向a，每次可以移动一个字符或者键入字符
     *
     * @param word
     * @return
     */
    public int minTimeToType(String word) {
        char cur = 'a';
        int count = 0;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            int a = Math.abs(ch - cur);
            int b = 26 - Math.abs(ch - cur);
            count += Math.min(a, b) + 1;
            cur = ch;
        }
        return count;
    }
}
