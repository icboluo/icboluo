package com.icboluo.leetcode.after_1000;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author icboluo
 * @since 2022-11-21 12:47
 */
public class N1002_公共字符 {
    public List<String> commonChars(String[] words) {
        int[] arr = new int[26];
        Arrays.fill(arr, Integer.MAX_VALUE);
        for (String word : words) {
            int[] temp = new int[26];
            for (int i = 0; i < word.length(); i++) {
                temp[word.charAt(i) - 'a']++;
            }
            for (int i = 0; i < arr.length; i++) {
                arr[i] = Math.min(arr[i], temp[i]);
            }
        }
        List<String> list = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0) {
                continue;
            }
            for (int j = 0; j < arr[i]; j++) {
                String s = Character.toString((char) (i + 'a'));
                list.add(s);
            }
        }
        return list;
    }
}
