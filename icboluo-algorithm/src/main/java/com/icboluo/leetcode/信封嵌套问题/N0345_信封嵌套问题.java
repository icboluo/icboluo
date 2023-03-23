package com.icboluo.leetcode.信封嵌套问题;

/**
 * 相当于：最长递增子序列
 *
 * @author icboluo
 * @since 2023-03-24 0:40
 */
class N0345_信封嵌套问题 {
    public static void main(String[] args) {
        var cla = new N0345_信封嵌套问题();
        int[][] arr = {
                {5, 4},
                {6, 4},
                {6, 7},
                {2, 3},
        };
        int count = cla.maxEnvelopes(arr);
        System.out.println("count = " + count);
    }

    /**
     * 对宽度进行升序，保证下面的比上面的不小
     *
     * @param envelopes
     * @return
     */
    public int maxEnvelopes(int[][] envelopes) {
        return -1;
    }
}
