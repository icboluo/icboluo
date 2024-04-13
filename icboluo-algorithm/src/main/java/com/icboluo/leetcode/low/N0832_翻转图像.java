package com.icboluo.leetcode.low;

import com.icboluo.util.ArrayHelper;

/**
 * @author icboluo
 * @since 2023-12-18 22:48
 */
class N0832_翻转图像 {
    @SuppressWarnings("all")
    public static void main(String[] args) {
        var cla = new N0832_翻转图像();
        int[][] arr1 = {
                {1, 1, 0}, // ----------------------------------
                {1, 0, 1}, // ----------------------------------
                {0, 0, 0}};
        ArrayHelper.print(cla.flipAndInvertImage(arr1));
        int[][] arr2 = {
                {1, 1, 0, 0}, // ----------------------------------
                {1, 0, 0, 1}, // ----------------------------------
                {0, 1, 1, 1}, // ----------------------------------
                {1, 0, 1, 0}};
        ArrayHelper.print(cla.flipAndInvertImage(arr2));
    }

    // 翻转图像 low
    public int[][] flipAndInvertImage(int[][] image) {
        for (int i = 0; i < image.length; i++) {
            for (int j = 0; j < (image[i].length + 1) / 2; j++) {
                // 1^1=0,0^1=1
                int temp = image[i][j] ^ 1;
                image[i][j] = image[i][image[i].length - j - 1] ^ 1;
                image[i][image[i].length - j - 1] = temp;
            }
        }
        return image;
    }
}
