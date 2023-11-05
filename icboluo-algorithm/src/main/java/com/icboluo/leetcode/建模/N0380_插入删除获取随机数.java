package com.icboluo.leetcode.建模;

import java.util.*;

/**
 * List 删除一个元素怎么保证时间复杂度为O(1)，首先，数组删除一个元素...是绝对不可能是心爱的
 * List 删除尾部元素可以不进行扩容
 *
 * @author icboluo
 * @since 2023-06-05 23:23
 */
class N0380_插入删除获取随机数 {
    // val:idx
    Map<Integer, Integer> map;

    // list的目的就是降低时间复杂度
    List<Integer> list;

    Random random = new Random();

    // 因为时间复杂度要求o(1)，所以必须要有map
    public N0380_插入删除获取随机数() {
        map = new HashMap<>();
        list = new ArrayList<>();
    }

    public boolean insert(int val) {
        if (map.containsKey(val)) {
            return false;
        }
        map.put(val, list.size());
        list.add(val);
        return true;
    }

    public boolean remove(int val) {
        if (!map.containsKey(val)) {
            return false;
        }
        Integer idx = map.get(val);
        // swap的目的是为了防止数组平移
        if (idx < list.size() - 1) {
            Integer last = list.get(list.size() - 1);
            list.set(idx, last);
            map.put(last, idx);
        }
        list.remove(list.size() - 1);
        map.remove(val);
        return true;
    }

    public int getRandom() {
        return list.get(random.nextInt(list.size()));
    }
}
