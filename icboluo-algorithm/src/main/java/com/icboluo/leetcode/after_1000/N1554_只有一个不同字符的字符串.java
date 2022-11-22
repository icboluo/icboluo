package com.icboluo.leetcode.after_1000;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author icboluo
 * @since 2022-11-22 12:43
 */
class N1554_只有一个不同字符的字符串 {
    public static void main(String[] args) {
        N1554_只有一个不同字符的字符串 cla = new N1554_只有一个不同字符的字符串();
        String[] arr1 = {"abcd", "acbd", "aacd"};
        String[] arr2 = {"abcd", "cd", "abyd", "abab"};
        String[] arr3 = {"ab", "cd", "yz"};
        System.out.println(cla.oneDifferent(arr1));
        System.out.println(cla.oneDifferent(arr2));
        System.out.println(cla.oneDifferent(arr3));
    }

    /**
     * 将某一位替换为*号判断是否有重复数据
     *
     * @param arr
     * @return
     */
    private boolean oneDifferent(String[] arr) {
        List<String> list = new ArrayList<>();
        for (String str : arr) {
            for (int i = 0; i < str.length(); i++) {
                String temp = str.substring(0, i) + "*" + str.substring(i + 1);
                list.add(temp);
            }
        }
        Set<String> set = new HashSet<>(list);
        return list.size() != set.size();
    }
}
