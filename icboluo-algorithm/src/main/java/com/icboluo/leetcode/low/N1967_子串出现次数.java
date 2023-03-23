package com.icboluo.leetcode.low;

/**
 * @author icboluo
 * @since 2023-03-23 23:27
 */
class N1967_子串出现次数 {
    public int numOfStrings(String[] patterns, String word) {
        int count = 0;
        for (String pattern : patterns) {
            if (word.contains(pattern)) {
                count++;
            }
        }
        return count;
    }
}
