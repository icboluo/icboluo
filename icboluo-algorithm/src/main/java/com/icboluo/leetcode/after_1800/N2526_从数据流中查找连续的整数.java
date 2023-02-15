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
     * 上一个value点的索引
     */
    int preIndex;

    /**
     * 2526 从数据流中查找连续的整数
     * 最后k个整数，值是否均为value
     *
     * @param value
     * @param k
     */
    public N2526_从数据流中查找连续的整数(int value, int k) {
        this.k = k;
        this.value = value;
        list = new ArrayList<>();
        preIndex = Integer.MAX_VALUE;
    }

    public boolean consec(int num) {
//        list.add(num);
//        if (list.size() < k) {
//            return false;
//        }
//        return list.stream().skip(list.size() - k).allMatch(n -> n == value);
        if (num == value) {
            list.add(num);
            if (preIndex == Integer.MAX_VALUE) {
                preIndex = list.size() - 1;
            }
            if (list.size() - preIndex < k) {
                return false;
            } else {
                return true;
            }
        } else {
            preIndex = Integer.MAX_VALUE;
            return false;
        }
    }
}
