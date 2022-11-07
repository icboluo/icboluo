package com.icboluo.leetcode;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author icboluo
 * @since 2022-11-07 21:35
 */
class 形近词 {
    public static void main(String[] args) {
        形近词 cla = new 形近词();
        String[] dictionary = {"aa", "ab", "ac"};
        String[] words = {"ac", "dd"};
        int[] ints = cla.queryWords(dictionary, words);
        System.out.println("ints = " + ints);
    }

    /**
     * dict存放所有单词，互不相同；words是所需要校验的字符串；将words中的每一个单词，能否只将这个单词中一个字母换成另一个字母，使形成的新单词存在于字典中（不包含单词本身），请统计该单词形近词在字典集中的数量，将结果放到数组中
     *
     * @param dictionary
     * @param words
     * @return
     */
    private int[] queryWords(String[] dictionary, String[] words) {
        // 无法避免的细节，我们不要去算法内部增加复杂度，这样可能造成时间复杂度提升
        // 简单来说，需要在前面就扩展需要处理的数据
        Function<String, List<String>> intFun = dict -> IntStream.range(0, dict.length()).mapToObj(i -> dict.substring(0, i) + "*" + dict.substring(i + 1)).toList();
        // 这里可以根据words中每一个单词的长度进行filter，但是时间复杂度为o(1),不做优化
        Map<String, Integer> dictMap = Arrays.stream(dictionary)
                .map(intFun)
                .flatMap(Collection::stream)
                .collect(Collectors.groupingBy(key -> key, Collectors.collectingAndThen(Collectors.toList(), List::size)));
        List<String> dictList = Arrays.stream(dictionary).toList();

        int[] arr = new int[words.length];
        for (int i = 0; i < words.length; i++) {
            String item = words[i];
            List<String> tempList = intFun.apply(item);
            // 此块是一个循环，如果words中item有重复，可以map优化
            for (String temp : tempList) {
                if (dictMap.containsKey(temp)) {
                    arr[i] = dictMap.get(temp);
                }
            }
            // item.length 代表生成了多少个形近词；如果重复abc会生成3个元素，需要排除
            if (dictList.contains(item)) {
                arr[i] -= item.length();
            }
        }
        return arr;
    }
}
