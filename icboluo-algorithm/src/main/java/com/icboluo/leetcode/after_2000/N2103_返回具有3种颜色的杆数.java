package com.icboluo.leetcode.after_2000;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;

/**
 * @author icboluo
 * @since 2023-03-21 23:51
 */
class N2103_返回具有3种颜色的杆数 {
    public int countPoints(String rings) {
        LinkedList<Character>[] arr = new LinkedList[10];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = new LinkedList<>();
        }
        for (int i = 0; i < rings.length(); i += 2) {
            char color = rings.charAt(i);
            int num = Character.digit(rings.charAt(i + 1), 10);
            arr[num].add(color);
        }
        return (int) Arrays.stream(arr).map(HashSet::new).filter(li -> li.size() == 3).count();
    }
}
