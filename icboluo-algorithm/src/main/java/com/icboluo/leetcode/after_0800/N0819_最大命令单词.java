package com.icboluo.leetcode.after_0800;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author icboluo
 * @since 2022-11-07 22:33
 */
class N0819_最大命令单词 {
    public static void main(String[] args) {
        N0819_最大命令单词 cla = new N0819_最大命令单词();
        String s = cla.mostCommonWord("Bob hit a ball, the hit BALL flew far after it was hit.", new String[]{"hit"});
        System.out.println("s = " + s);
    }

    // ERROR 小数点拆分错误
    public String mostCommonWord(String paragraph, String[] banned) {
        Set<String> set = new HashSet<>(Arrays.asList(banned));
        // 拆分字符串
        List<String> list = new ArrayList<>();
        String temp = "";
        for (int i = 0; i < paragraph.length(); i++) {
            if (Character.isLetter(paragraph.charAt(i))) {
                temp += paragraph.charAt(i);
                // 因为不光是空格间隔，还有其他字符，多增加一个过滤
            } else if (!"".equals(temp)) {
                list.add(temp);
                temp = "";
            }
        }
        if (!"".equals(temp)) {
            list.add(temp);
        }
        Map<String, Integer> strCountMap = list.stream()
                .map(String::toLowerCase)
                .filter(str -> !set.contains(str))
                .collect(Collectors.groupingBy(str -> str, Collectors.collectingAndThen(Collectors.toList(), List::size)));
        return strCountMap.entrySet().stream()
                .min((a, b) -> b.getValue() - a.getValue())
                .map(Map.Entry::getKey)
                .orElse("");
    }
}
