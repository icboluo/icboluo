package com.icboluo.leetcode.最长回文串;

import java.util.HashSet;

/**
 * @author icboluo
 * @since 2022-11-26 11:48
 */
class N0409_能构成的最长回文串 {
    public int longestPalindrome(String s) {
        HashSet<Character> set = new HashSet<>();
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (set.contains(s.charAt(i))) {
                set.remove(s.charAt(i));
                count++;
            }else{
                set.add(s.charAt(i));
            }
        }
        return set.isEmpty() ? 2 * count : 2 * count + 1;
    }
}
