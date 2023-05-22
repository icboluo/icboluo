package com.icboluo.datastructure.recursion;

/**
 * 用递归实现不死神兔
 * 故事得从西元1202年说起，话说有一位意大利青年，名叫斐波那契。
 * 在他的一部著作中提出了一个有趣的问题：假设一对刚出生的小兔一个月后就能长成大兔，
 * 再过一个月就能生下一对小兔，并且此后每个月都生一对小兔，没有发生死亡，
 * 问：现有一对刚出生的兔子2年后(24个月)会有多少对兔子?
 *
 * @author icboluo
 */
class Rabbit {
    public static void main(String[] args) {
        System.out.println(getRabbitNums(24));
        getNums();

    }

    private static int getRabbitNums(int month) {
        if (month == 1 || month == 2) {
            return 1;
        } else {
            return getRabbitNums(month - 1) + getRabbitNums(month - 2);
        }
    }

    private static void getNums() {
        int month = 24;
        int num1 = 1;
        int num2 = 1;
        for (int i = 3; i <= month; i++) {
            int count = num1 + num2;
            num1 = num2;
            num2 = count;
        }
        System.out.println(num2);
    }
}
