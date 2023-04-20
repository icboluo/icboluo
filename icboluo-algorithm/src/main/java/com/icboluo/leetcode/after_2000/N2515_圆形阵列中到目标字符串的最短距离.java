package com.icboluo.leetcode.after_2000;

import java.util.Objects;

/**
 * @author icboluo
 * @since 2023-04-21 0:20
 */
class N2515_圆形阵列中到目标字符串的最短距离 {
    /**
     * 每次操作可以移动到下一个或上一个单词，返回到达目标字符串所需要的最短距离 FIXME ERROR 期待25 实际0
     * 方案2：数组元素挨着遍历，取左右min即可
     *
     * @param words      环形数组
     * @param target
     * @param startIndex
     * @return
     */
    public int closetTarget(String[] words, String target, int startIndex) {
        for (int i = 0; i < (words.length + 1) / 2; i++) {
            if (Objects.equals(words[(startIndex + i) % words.length], target)
                    || Objects.equals(words[(startIndex - i + words.length) % words.length], target)) {
                return i;
            }
        }
        return -1;
    }
}
