package com.icboluo.leetcode.after_0000;

import java.util.LinkedList;
import java.util.List;

/**
 * @author icboluo
 * @since 2022-11-07 21:57
 */
class N0139_0140单词分割 {
    // 递归暴力解，复杂问题简单化；可以map优化 TODO error
    public boolean wordBreak(String s, List<String> wordDict) {
        if (s.length() == 0) {
            return true;
        }
        for (int i = 0; i < s.length(); i++) {
            String pre = s.substring(0, i);
            String suffix = s.substring(i);
            if (wordDict.contains(pre) && wordBreak(suffix, wordDict)) {
                return true;
            }
        }
        return false;
    }

    // 0140，相信递归可以完成这件事
    public List<String> wordBreak2(String s, List<String> wordDict) {
        LinkedList<String> ans = new LinkedList<>();
        for (String word : wordDict) {
            if (s.startsWith(word)) {
                if (s.length() == word.length()) {
                    ans.add(word);
                } else {
                    List<String> strings = wordBreak2(s.substring(word.length()), wordDict);
                    for (String string : strings) {
                        ans.add(word + " " + string);
                    }
                }
            }
        }
        return ans;
    }
}
