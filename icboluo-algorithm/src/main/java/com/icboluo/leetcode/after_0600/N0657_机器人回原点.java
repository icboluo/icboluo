package com.icboluo.leetcode.after_0600;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author icboluo
 * @since 2022-12-05 21:17
 */
class N0657_机器人回原点 {
    public boolean judgeCircle(String moves) {
        // 当然了，用i++--;j++-- 的方式也是可以的
        Map<Character, Integer> charCountMap = IntStream.range(0, moves.length())
                .mapToObj(moves::charAt)
                .collect(Collectors.groupingBy(ch -> ch, Collectors.collectingAndThen(Collectors.toList(), List::size)));
        if (!Objects.equals(charCountMap.get('L'), charCountMap.get('R'))) {
            return false;
        }
        return Objects.equals(charCountMap.get('U'), charCountMap.get('D'));
    }
}
