package com.icboluo.leetcode.after_1300;

import java.util.ArrayList;
import java.util.List;

/**
 * @author icboluo
 * @since 2023-03-29 23:38
 */
class N1656_设计有序流 {
    String[] arr;
    int pre;

    /**
     * @param n 数组大小 TODO 再看一遍，防止不会
     */
    public N1656_设计有序流(int n) {
        arr = new String[n];
        pre = 0;
    }

    /**
     * TODO 2424
     *
     * @param idKey 数组第几个位置放元素
     * @param value 放入的值
     * @return 从当前到最后最长连续串
     */
    public List<String> insert(int idKey, String value) {
        arr[idKey - 1] = value;
        List<String> list = new ArrayList<>();
        while (pre < arr.length && arr[pre] != null) {
            list.add(arr[pre++]);
        }
        return list;
    }
}
