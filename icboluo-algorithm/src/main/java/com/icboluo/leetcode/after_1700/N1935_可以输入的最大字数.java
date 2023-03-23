package com.icboluo.leetcode.after_1700;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author icboluo
 * @since 2023-03-23 23:34
 */
class N1935_可以输入的最大字数 {
    /**
     * @param text          需要输入的单词
     * @param brokenLetters 坏的键
     * @return 可以输入成功多少个
     */
    public int canBeTypedWords(String text, String brokenLetters) {
        String[] arr = text.split(" ");
        Set<Character> set = IntStream.range(0, brokenLetters.length())
                .mapToObj(brokenLetters::charAt)
                .collect(Collectors.toSet());
        int count = 0;
        for (String str : arr) {
            for (int i = 0; i < str.length(); i++) {
                if (set.contains(str.charAt(i))) {
                    count--;
                    break;
                }
            }
            count++;
        }
        return count;
    }
}
