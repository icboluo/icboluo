package com.icboluo.datastructure.recursion;

class 猴子吃桃 {
    /**
     * 描述:猴子吃桃子问题，猴子第一天摘下若干个桃子，当即吃了快一半，还不过瘾，又多吃了一个。
     * 第二天又将仅剩下的桃子吃掉了一半，又多吃了一个。以后每天都吃了前一天剩下的一半多一个。到第十天，
     * 只剩下一个桃子。试求第一天共摘了多少桃子？
     */
    public static void main(String[] args) {
        System.out.println(peachNum(8));
    }

    private static int peachNum(int day) {
        if (day == 10) {
            return 1;
        } else {
            return peachNum(day + 1) * 2 + 2;

        }
    }
}
