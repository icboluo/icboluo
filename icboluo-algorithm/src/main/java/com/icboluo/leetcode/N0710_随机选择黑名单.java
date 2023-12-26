package com.icboluo.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author icboluo
 * @since 2023-12-26 8:31
 */
class N0710_随机选择黑名单 {
    // 正常元素的个数 没看懂
    int white;

    Map<Integer, Integer> blackMap;

    /**
     * 构造函数，这个代码写的特垃圾，正常情况下绝对写不出来
     *
     * @param n         [0,n)
     * @param blacklist 包含黑名单
     */
    public N0710_随机选择黑名单(int n, int[] blacklist) {
        white = n - blacklist.length;
        blackMap = new HashMap<>();
        int curMax = white;
        // 我越看越觉得这串代码是个垃圾
        for (int ele : blacklist) {
            // 如果黑名单比最大的索引还要大，则说明取不到
            if (ele >= curMax) {
                continue;
            }
            if (blackMap.containsKey(curMax)) {
                curMax++;
            }
            blackMap.put(ele, curMax++);
        }
    }

    /**
     * 在区间[0,n)上随机选取一个元素返回，这个元素不能是黑名单中的元素
     *
     * @return
     */
    public int pick() {
        int index = (int) (Math.random() * white);
        if (blackMap.containsKey(index)) {
            return blackMap.get(index);
        }
        return index;
    }
}
