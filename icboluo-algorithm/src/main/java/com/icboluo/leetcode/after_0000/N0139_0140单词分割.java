package com.icboluo.leetcode.after_0000;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author icboluo
 * @since 2022-11-07 21:57
 */
class N0139_0140单词分割 {
    public static void main(String[] args) {
        N0139_0140单词分割 cla = new N0139_0140单词分割();
        boolean b = cla.wordBreak("leetcode", Arrays.asList("leet", "code"));
        System.out.println("b = " + b);
    }

    // 递归暴力解，复杂问题简单化；可以map优化 TODO 超时
    public boolean wordBreak(String s, List<String> wordDict) {
        if (s.length() == 0) {
            return true;
        }
        // 我们需要从1开始，length结束；因为length==0的判断是下一层方法，尽可能的让suffix为0，而不是pre
        for (int i = 1; i <= s.length(); i++) {
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
