package com.icboluo.algorithm.FloodFill;

/**
 * @author icboluo
 * @since 2022-06-28 22:33
 */
class N0733_FloodFill算法 {
    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        // 题目中说，起始像素与目标color一致的时候，直接结束
        if (image[sr][sc] == color) {
            return image;
        }
        dfs(image, sr, sc, color, image[sr][sc]);
        return image;
    }

    public void dfs(int[][] arr, int i, int j, int color, int sourceColor) {
        if (i < 0 || j < 0 || i >= arr.length || j >= arr[0].length) {
            return;
        }
        // 如果当前元素和初始元素不相等
        if (arr[i][j] != sourceColor) {
            return;
        }
        arr[i][j] = color;
        dfs(arr, i - 1, j, color, sourceColor);
        dfs(arr, i + 1, j, color, sourceColor);
        dfs(arr, i, j - 1, color, sourceColor);
        dfs(arr, i, j + 1, color, sourceColor);
    }

    public static void main(String[] args) {
        var cla = new N0733_FloodFill算法();
        int[][] arr = new int[][]{
                {1, 1, 1},
                {1, 1, 0},
                {1, 0, 1},
        };
        cla.floodFill(arr, 1, 1, 2);
    }
}
