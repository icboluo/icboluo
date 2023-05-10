package com.icboluo.leetcode.stack;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author icboluo
 * @since 2023-04-19 21:36
 */
public class Other_元素出现次数最多为2 {
    public static void main(String[] args) {
        System.out.println(removeDuplicateLetters("34533"));// 4533
        System.out.println(removeDuplicateLetters("5445795045"));// 5479504
    }

    public static String removeDuplicateLetters(String s) {
        // 剩余元素数量
        Map<Character, Integer> map = IntStream.range(0, s.length()).mapToObj(s::charAt)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.summingInt(ele -> 1)));
        Deque<Character> stack = new ArrayDeque<>();
        // 栈中元素数量
        Map<Character, Integer> stackMap = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            Character peek = stack.peek();
            // 当总元素数量少于2的时候不要删除
            while (!stack.isEmpty() && map.containsKey(peek) && peek < ch && map.get(peek) + stackMap.getOrDefault(peek, 0) > 2) {
                stackMap.put(peek, stackMap.get(peek) - 1);
                stack.pop();
            }
            // 当栈中元素数量等于2的时候，不要新增
            if (stackMap.getOrDefault(ch, 0) < 2) {
                stack.push(ch);
                stackMap.put(ch, stackMap.getOrDefault(ch, 0) + 1);
            }
            if (map.get(ch) == 1) {
                map.remove(ch);
            }
            map.put(ch, map.get(ch) - 1);
        }
        // 打印结果
        StringBuilder ans = new StringBuilder();
        while (!stack.isEmpty()) {
            ans.insert(0, stack.pop());
        }
        return ans.toString();
    }
}
