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
     * 对宽度进行升序，保证下面的比上面的大
     * 对高度进行降序，保证同一宽度下面比上面的小（这样的话就不能出现同一宽度信封可以互相嵌套的情况了
     *
     * @param envelopes
     * @return
     */
    public int maxEnvelopes(int[][] envelopes) {
        sort(envelopes);
        for (int i = 0; i < envelopes.length; i++) {

        }
        return -1;
    }

    private void sort(int[][] arr) {

    }
}
