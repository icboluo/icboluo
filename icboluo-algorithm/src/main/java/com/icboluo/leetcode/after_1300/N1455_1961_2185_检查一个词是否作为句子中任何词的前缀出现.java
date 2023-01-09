package com.icboluo.leetcode.after_1300;

import java.util.Arrays;

/**
 * @author icboluo
 * @since 2023-01-07 16:43
 */
class N1455_1961_2185_检查一个词是否作为句子中任何词的前缀出现 {
    /**
     * 1455 字符串是第一个前缀子串
     *
     * @param sentence
     * @param searchWord
     * @return
     */
    public int isPrefixOfWord(String sentence, String searchWord) {
        String[] arr = sentence.split(" ");
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].startsWith(searchWord)) {
                return i + 1;
            }
        }
        return -1;
    }

    /**
     * 1961 检查字符串是否是数组的前缀；
     *
     * @param s     s可以由word连续构成
     * @param words
     * @return
     */
    public boolean isPrefixString(String s, String[] words) {
        String res = "";
        for (String word : words) {
            res += word;
            if (res.equals(s)) {
                return true;
            }
            if (!s.contains(res)) {
                return false;
            }
        }
        return false;
    }

    /**
     * 2185 计算给定前缀的单词
     *
     * @param words
     * @param pref
     * @return
     */
    public int prefixCount(String[] words, String pref) {
        return (int) Arrays.stream(words).filter(str -> str.startsWith(pref)).count();
    }

    /**
     * 2255 计算给定字符串的前缀
     *
     * @param words
     * @param s
     * @return
     */
    public int countPrefixes(String[] words, String s) {
        return (int) Arrays.stream(words).filter(s::startsWith).count();
    }
}
