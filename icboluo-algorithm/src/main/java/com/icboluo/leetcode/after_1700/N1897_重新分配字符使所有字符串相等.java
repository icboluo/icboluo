package com.icboluo.leetcode.after_1700;

/**
 * @author icboluo
 * @since 2023-03-24 0:01
 */
class N1897_重新分配字符使所有字符串相等 {
    /**
     * 先汇总，再分配；暴力解即可
     *
     * @param words
     * @return
     */
    public boolean makeEqual(String[] words) {
        int[] arr = new int[26];
        for (String word : words) {
            for (int i = 0; i < word.length(); i++) {
                arr[word.charAt(i) - 'a']++;
            }
        }
        for (int item : arr) {
            if (item % words.length != 0) {
                return false;
            }
        }
        return true;
    }
}
