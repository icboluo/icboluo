package com.icboluo.leetcode.消消乐;

/**
 * @author icboluo
 * @since 2023-03-30 21:53
 */
class N2582_通过枕头 {
    // 从头至尾，从尾至头，求最终索引
    public int passThePillow(int n, int time) {
        time = time % (2 * n - 2);
        // 这块不是从第0个开始的，是从第1个开始的，需要等于号
        if (time >= n) {
            return 2 * n - time - 1;
        }
        return time + 1;
    }
}
