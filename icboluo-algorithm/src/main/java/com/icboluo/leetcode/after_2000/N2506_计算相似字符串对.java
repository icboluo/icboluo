package com.icboluo.leetcode.after_2000;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author icboluo
 * @since 2023-04-21 0:15
 */
class N2506_计算相似字符串对 {
    /**
     * 2个字符串拥有相同的字符，说明相似，遍历加法问题
     * 理论上来说，可能用到组合的解法，可是解法过于困难了
     *
     * @param words
     * @return
     */
    public int similarPairs(String[] words) {
        Map<Set<Character>, Integer> map = new HashMap<>();
        int res = 0;
        for (String word : words) {
            Set<Character> set = IntStream.range(0, word.length()).mapToObj(word::charAt).collect(Collectors.toSet());
            // 对于第一个数来说，他出现的对数是0，第二个数出现的对数是1，第三个出现的对数是1+2，所以使用这样的加法
            res += map.getOrDefault(set, 0);
            map.merge(set, 1, Integer::sum);
        }
        return res;
    }
}
