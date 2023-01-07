package com.icboluo.leetcode.子串子序列;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author icboluo
 * @since 2023-01-07 22:22
 */
class N1408_数组中的字符串匹配 {
    /**
     * 如果一个是一个的子串，说明匹配，返回所有的子串
     *
     * @param words
     * @return
     */
    public List<String> stringMatching(String[] words) {
        // 子串只能是长的包短的，所以倒序
        Arrays.sort(words, (a, b) -> b.length() - a.length());
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < words.length - 1; i++) {
            for (int j = i + 1; j < words.length; j++) {
                // 加速
                if (res.contains(i)) {
                    break;
                }
                if (words[i].contains(words[j]) && !res.contains(j)) {
                    res.add(j);
                }
            }
        }
        return res.stream().map(i -> words[i]).toList();
    }
}
