package com.icboluo.leetcode.fivehundred;

import java.util.*;

/**
 * @author icboluo
 * @since 2022-09-20 0:11
 */
class N0127_顺序转换 {
    public static void main(String[] args) {
        N0127_顺序转换 cla = new N0127_顺序转换();
        int i = cla.ladderLength("ab", "xy", Arrays.asList("ac", "bc", "bd", "dy", "xy", "ay"));
        System.out.println(i);
    }

    private int ladderLength(String begin, String end, List<String> wordList) {
        Set<String> set = new HashSet<>(wordList);
        if (!set.contains(end)) {
            return -1;
        }
        Queue<String> queue = new LinkedList<>();
        queue.add(begin);
        Set<String> visited = new HashSet<>();
        visited.add(begin);
        int change = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            // 我们需要尽可能的找，能找到多少就找到多少，这里使用的是BFS
            for (int i = 0; i < size; i++) {
                String poll = queue.poll();
                assert poll != null;
                if (poll.equals(end)) {
                    return change;
                }
                for (int j = 0; j < poll.length(); j++) {
                    for (int k = 'a'; k < 'z'; k++) {
                        char[] arr = poll.toCharArray();
                        arr[j] = (char) k;
                        String str = new String(arr);
                        if (set.contains(str) && !visited.contains(str)) {
                            System.out.println(str);
                            queue.add(str);
                            visited.add(str);
                        }
                    }
                }
            }
            change++;
        }
        return 0;
    }
}
