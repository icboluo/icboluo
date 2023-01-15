package com.icboluo.leetcode.after_1800;

import java.util.ArrayList;
import java.util.List;

/**
 * @author icboluo
 * @since 2023-01-15 20:05
 */
class N2526_从数据流中查找连续的整数 {
    int k;
    int value;
    List<Integer> list;

    /**
     * 2526 从数据流中查找连续的整数 FIXME 超时
     * 最后k个整数，值是否均为value
     *
     * @param value
     * @param k
     */
    public N2526_从数据流中查找连续的整数(int value, int k) {
        this.k = k;
        this.value = value;
        list = new ArrayList<>();
    }

    public boolean consec(int num) {
        list.add(num);
        if (list.size() < k) {
            return false;
        }
        return list.stream().skip(list.size() - k).allMatch(n -> n == value);
    }
}
