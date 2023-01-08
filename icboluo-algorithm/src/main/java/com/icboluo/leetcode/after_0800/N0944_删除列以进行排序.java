package com.icboluo.leetcode.after_0800;

/**
 * @author icboluo
 * @since 2023-01-08 0:02
 */
class N0944_删除列以进行排序 {
    /**
     * 删除字符串中的每一列未按升序排列的列（列代表索引相同的字符，其实可以把这个字符串数组看成是二维数组
     *
     * @param strs
     * @return
     */
    public int minDeletionSize(String[] strs) {
        int count = 0;
        // 列循环
        for (int i = 0; i < strs[0].length(); i++) {
            char pre = 0;
            // 行循环
            for (int j = 0; j < strs.length; j++) {
                char cur = strs[j].charAt(i);
                // 列不符合
                if (cur < pre) {
                    count++;
                    break;
                }
                pre = cur;
            }
        }
        return count;
    }
}
