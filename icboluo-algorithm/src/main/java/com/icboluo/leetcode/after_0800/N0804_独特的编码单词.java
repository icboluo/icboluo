package com.icboluo.leetcode.after_0800;

import java.util.HashSet;
import java.util.Set;

/**
 * @author icboluo
 * @since 2022-11-07 22:46
 */
class N0804_独特的编码单词 {
    public int uniqueMorseRepresentations(String[] words) {
        String[] arr = new String[]{
                ".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..", "--", "-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--.."
        };

        Set<String> set = new HashSet<>();
        for (String word : words) {
            String temp = "";
            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                temp += arr[ch - 'a'];
            }
            set.add(temp);
        }
        return set.size();
    }
}
