package com.icboluo.datastructure.sparsearray;

import com.icboluo.util.ArrayHelper;
import org.junit.Test;

/**
 * @author lp
 */
class SparseArray {
    /**
     * 五子棋案例：
     * problem：当游戏刚开始棋盘上有好多空位置，如果存储会造成浪费
     * 稀疏数组的第一行3个参数：几行、几列、有多少不是（0）的
     * 把具有不同值的元素的行、列、值记录在一个小规模数组中，缩小规模
     */
    public static void main(String[] args) {
        //创建原始的二维数组 11*11，0代表没有棋子，1代表黑子，2代表白子
        int[][] chessArr = new int[11][11];
        chessArr[1][2] = 1;
        chessArr[2][3] = 2;
        ArrayHelper.print(chessArr);
        //非0数据的个数
        int sum = 0;
        for (int[] value : chessArr) {
            for (int j = 0; j < 11; j++) {
                if (value[j] != 0) {
                    sum++;
                }
            }
        }
        //创建对应的稀疏数组
        int[][] sparseArr = new int[sum + 1][3];
        //给稀疏数组第一行赋值
        sparseArr[0][0] = 11;
        sparseArr[0][1] = 11;
        sparseArr[0][2] = sum;

        //用于记录是第几个非0数据
        int count = 0;
        for (int i = 0; i < chessArr.length; i++) {
            for (int j = 0; j < 11; j++) {
                if (chessArr[i][j] != 0) {
                    count++;
                    sparseArr[count][0] = i;
                    sparseArr[count][1] = j;
                    sparseArr[count][2] = chessArr[i][j];
                }
            }
        }
        System.out.println("---------------------------------");
        System.out.println("得到的稀疏数组为");
        for (int[] ints : sparseArr) {
            System.out.printf("%d\t%d\t%d\t\n", ints[0], ints[1], ints[2]);
        }
        //2.1读取稀疏数组的第一行，根据第一行的数据，创建原始的二维数组
        int[][] chessArr2 = new int[sparseArr[0][0]][sparseArr[0][1]];
        //2.2读取稀疏数组的后面所有行，赋值给原数组
        for (int i = 1; i < sparseArr.length; i++) {
            chessArr2[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
        }
        System.out.println("---------------------------------");
        System.out.println("还原稀疏数组后的原棋盘为");
        ArrayHelper.print(chessArr2);
    }
}
