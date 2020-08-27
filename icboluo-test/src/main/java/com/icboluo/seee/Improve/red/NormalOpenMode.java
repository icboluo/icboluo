package com.icboluo.seee.Improve.red;

import java.util.ArrayList;

public class NormalOpenMode implements OpenMode {
    /*
    divide分红包
    totalMoney：总共的红包金额
    totalCount：红包的个数
     */
    @Override
    public ArrayList<Integer> divide(int totalMoney, int totalCount) {
        //平分红包返回
        //计算出平均值，前面totalCount-1个红包就放平均值，最后一个红包就装剩下的钱
        int avg = totalMoney / totalCount;
        //前面totalCount-1个红包就放平均值
        ArrayList<Integer> redbags = new ArrayList<>();
        for (int i = 0; i < totalCount-1; i++) {
            redbags.add(avg);
        }
        //循环结束还剩下一个，装到最后一个红包中
        redbags.add(totalMoney - avg * (totalCount - 1));
        return redbags;
    }
}
