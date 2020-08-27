package com.icboluo.se.compare;

import java.util.Comparator;

/**
 * @author icboluo
 * @date 2020-08-07 16:12
 */
class MyComparator implements Comparator<Integer> {
    /**
     * 比较器
     *
     * @param o1 first
     * @param o2 second
     * @return 0 不排序，o1-o2代表正序，o2-o1代表逆序
     */
    @Override
    public int compare(Integer o1, Integer o2) {
        return o2 - o1;
    }
}
