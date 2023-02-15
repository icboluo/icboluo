package com.icboluo.leetcode.二维数组;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author icboluo
 * @since 2023-02-15 23:27
 */
class N1640_是否能够连接形成数组 {
    /**
     * 二维数组能否连接形成arr 排序问题
     *
     * @param arr
     * @param pieces
     * @return
     */
    public boolean canFormArray(int[] arr, int[][] pieces) {
        Map<Integer, Integer> eleIdxMap = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            eleIdxMap.put(arr[i], i);
        }
        // 不包含处理
        for (int[] piece : pieces) {
            if (!eleIdxMap.containsKey(piece[0])) {
                return false;
            }
        }
        // 使二维数组安装一维数组的顺序排序
        Arrays.sort(pieces, (a, b) -> eleIdxMap.get(a[0]) - eleIdxMap.get(b[0]));
        int[] ints = new int[arr.length];
        int destPos = 0;
        for (int[] piece : pieces) {
            System.arraycopy(piece, 0, ints, destPos, piece.length);
            destPos += piece.length;
        }
        return Arrays.equals(ints, arr);
    }
}
