package com.icboluo.leetcode.after_1000;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author icboluo
 * @since 2022-12-28 22:25
 */
class N1160_字符串是否包含数组元素 {
    public int countCharacters(String[] words, String chars) {
        Character[] charArr = new Character[chars.length()];
        for (int i = 0; i < charArr.length; i++) {
            charArr[i] = chars.charAt(i);
        }
        Map<Character, Integer> charCountMap = Arrays.stream(charArr)
                .collect(Collectors.groupingBy(ch -> ch, Collectors.collectingAndThen(Collectors.toList(), List::size)));
        int sum = 0;
        for (String word : words) {
            if (coain(word, charCountMap)) {
                sum += word.length();
            }
        }
        return sum;
    }

    private boolean coain(String str, Map<Character, Integer> charCountMap) {
        Character[] charArr = new Character[str.length()];
        for (int i = 0; i < charArr.length; i++) {
            charArr[i] = str.charAt(i);
        }
        Map<Character, Integer> tempMap = Arrays.stream(charArr)
                .collect(Collectors.groupingBy(ch -> ch, Collectors.collectingAndThen(Collectors.toList(), List::size)));
        for (Map.Entry<Character, Integer> entry : tempMap.entrySet()) {
            Character key = entry.getKey();
            Integer value = entry.getValue();
            if (!charCountMap.containsKey(key) || charCountMap.get(key) < value) {
                return false;
            }
        }
        return true;
    }
}
