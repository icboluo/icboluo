package com.icboluo.leetcode.图形;

/**
 * @author icboluo
 * @since 2023-12-26 8:29
 */
class N0836_判断矩形是否重叠 {
    // 矩形重叠 判断矩形是否重叠
    public boolean isRectangleOverlap(int[] rec1, int[] rec2) {
        return !(rec1[0] >= rec2[2] || rec1[1] >= rec2[3] || rec2[0] >= rec1[2] || rec2[1] >= rec1[3]);
    }
}
