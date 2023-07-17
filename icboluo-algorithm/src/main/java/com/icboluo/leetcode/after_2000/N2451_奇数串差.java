package com.icboluo.leetcode.after_2000;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

/**
 * @author icboluo
 * @since 2023-04-20 23:34
 */
class N2451_奇数串差 {
    /**
     * 字符间距离相等
     *
     * @param words
     * @return
     */
    public String oddString(String[] words) {
        Map<List<Integer>, List<String>> map = new HashMap<>();
        for (String word : words) {
            List<Integer> list = IntStream.range(0, word.length() - 1)
                    .mapToObj(i -> word.charAt(i + 1) - word.charAt(i))
                    .toList();
            // list的去重是里面的元素来的
            if (map.containsKey(list)) {
                map.get(list).add(word);
            } else {
                List<String> temp = new ArrayList<>();
                temp.add(word);
                map.put(list, temp);
            }
        }
        return map.values().stream().filter(val -> val.size() == 1).map(li -> li.get(0)).findFirst().orElse("");
    }
}
