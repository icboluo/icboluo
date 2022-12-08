package com.icboluo.leetcode.after_1000;

import java.util.ArrayList;
import java.util.List;

/**
 * @author icboluo
 * @since 2022-12-08 23:13
 */
class N1078_查找前2个单词后面的单词 {
    // TODO ERROR
    public String[] findOcurrences(String text, String first, String second) {
        int idx = 0;
        List<String> list = new ArrayList<>();
        String two = first + " " + second;
        while (idx != -1) {
            idx = text.indexOf(two, idx);
            int start = idx + two.length();
            int end = start;
            // 找到third的start和end
            while (end < text.length() && text.charAt(end) != ' ') {
                end++;
            }
            // 如果存在，并且不是最后一个单词
            if (idx != -1 && start < text.length()) {
                // 如果是首个，或者first前面是空格，说明是合法的
                if (idx == 0 || text.charAt(idx - 1) == ' ') {
                    list.add(text.substring(start, end));
                }
            } else {
                break;
            }
            idx = idx + first.length() + 1;
        }
        return list.toArray(new String[0]);
    }

    /**
     * 拆分成字符串数组
     *
     * @param text
     * @param first
     * @param second
     * @return
     */
    public String[] findOcurrences2(String text, String first, String second) {
        String[] words = text.split(" ");
        List<String> list = new ArrayList<>();
        for (int i = 2; i < words.length; i++) {
            if (first.equals(words[i - 2]) && second.equals(words[i - 1])) {
                list.add(words[i]);
            }
        }
        return list.toArray(new String[0]);
    }
}
