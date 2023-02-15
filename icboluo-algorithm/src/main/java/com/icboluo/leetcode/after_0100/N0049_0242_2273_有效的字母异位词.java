package com.icboluo.leetcode.after_0100;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author icboluo
 * @since 2022-10-31 13:15
 */
class N0049_0242_2273_有效的字母异位词 {
    public static void main(String[] args) {
        var cla = new N0049_0242_2273_有效的字母异位词();
        String str1 = "anagram";
        String str2 = "nagaram";
        boolean b = cla.isAnagram1(str1, str2);
        System.out.println("b = " + b);
        System.out.println("cla.removeAnagrams(new String[]{}) = " + cla.removeAnagrams2(new String[]{"a", "b", "a"}));
    }

    /**
     * 0049 分组字母异位词
     *
     * @param strs
     * @return
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        // 主要是 Map<Map<Character, Integer>, List<String>> 这个的构建，key也可以是排序好的字符串，目的就是判断是否相似而已
        Map<Map<Character, Integer>, List<String>> res = Arrays.stream(strs)
                .collect(Collectors.groupingBy(str ->
                        IntStream.range(0, str.length())
                                .mapToObj(str::charAt).collect(Collectors.groupingBy(ch -> ch, Collectors.summingInt(ele -> 1)))));
        return new ArrayList<>(res.values());
    }

    /**
     * 0242 判断s和t是否互相相似
     * 排序
     *
     * @param str1
     * @param str2
     * @return
     */
    public boolean isAnagram1(String str1, String str2) {
        if (str1 == null || str2 == null || str1.length() != str2.length()) {
            return false;
        }
        char[] chars1 = str1.toCharArray();
        char[] chars2 = str2.toCharArray();
        Arrays.sort(chars1);
        Arrays.sort(chars2);
        return Arrays.equals(chars1, chars2);
    }

    /**
     * 哈希/数组
     *
     * @param s
     * @param t
     * @return
     */
    public boolean isAnagram2(String s, String t) {
        // 可以判断长度加速，短的肯定不能包含长的
        // 26个字母的 eleCountMap
        int[] arr = new int[26];
        for (int i = 0; i < s.length(); i++) {
            arr[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < t.length(); i++) {
            arr[t.charAt(i) - 'a']--;
            // 可以判断小于0加速
        }
        // 可以for循环return加速
        return Arrays.stream(arr).filter(time -> time != 0).count() == 0;
    }

    /**
     * 2273 移除字母异位词后的结果数组；把长的相似的去重（需要是相邻的 ERROR
     *
     * @param words
     * @return
     */
    public List<String> removeAnagrams1(String[] words) {
        Set<Map<Character, Integer>> set = new HashSet<>();
        List<String> res = new ArrayList<>();
        for (String word : words) {
            Map<Character, Integer> eleCountMap = IntStream.range(0, word.length())
                    .mapToObj(word::charAt)
                    .collect(Collectors.groupingBy(ch -> ch, Collectors.summingInt(ele -> 1)));
            if (set.add(eleCountMap)) {
                res.add(word);
            }
        }
        return res;
    }

    public List<String> removeAnagrams2(String[] words) {
        List<String> res = new ArrayList<>();
        Map<Character, Integer> preMap = new HashMap<>();
        for (String word : words) {
            Map<Character, Integer> eleCountMap = IntStream.range(0, word.length())
                    .mapToObj(word::charAt)
                    .collect(Collectors.groupingBy(ch -> ch, Collectors.summingInt(ele -> 1)));
            if (!preMap.equals(eleCountMap)) {
                res.add(word);
                preMap = eleCountMap;
            }
        }
        return res;
    }
}
