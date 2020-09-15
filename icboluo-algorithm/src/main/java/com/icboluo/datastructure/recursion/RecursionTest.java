package com.icboluo.datastructure.recursion;


/**
 * @author icboluo
 */
class RecursionTest {
    public static void main(String[] args) {
        test1(4);
        int factorial = factorial(3);
        System.out.println("factorial = " + factorial);

    }

    /**
     * 如果没有终止条件，会发生StackOverflowError  栈内存溢出错误! (避免)
     * 打印问题,递归回溯问题
     *
     * @param n
     */
    public static void test1(int n) {
        if (n > 2) {
            test1(n - 1);
        }
        System.out.println("n = " + n);
    }

    /**
     * 阶乘问题
     *
     * @param n
     * @return
     */
    public static int factorial(int n) {
        if (n == 1) {
            return 1;
        } else {
            return factorial((n - 1)) * n;
        }
    }

    /**
     * 求5的阶乘
     * 需求 : 求 5 的阶乘.
     * 请问 : 5的阶乘 = 5 * 4的阶乘.
     * 请问 : 4的阶乘 = 4 * 3的阶乘.
     * 请问 : 3的阶乘 = 3 * 2的阶乘.
     * 请问 : 2的阶乘 = 2 * 1的阶乘.
     * 请问 : 1的阶乘 = 1;
     * <p>
     * 思考一 : 如何结束递归. 如果寻求 1 的阶乘, 返回 1 即可.
     * 思考二 : 如何继续递归. 如果不是寻求 1 的阶乘. 就继续.
     *
     * @param n 5
     */
    public static int jiecheng(int n) {

        // 需求 : 求 5 的阶乘.
        // 方式一 : 打印
        int result1 = 5 * 4 * 3 * 2;

        // 方式二 : 循环
        int sum = 1;
        for (int i = 5; i >= 1; i--) {
            sum *= i;
        }
        //方式三 : 递归
        if (n == 1) {
            return 1;
        } else {
            return n * jiecheng(n - 1);
        }
    }

    /**
     * 有 5 个人围成一圈.
     * 问 : 第 5 个人多少 ? 我比第 4 个人大 2 岁.
     * 问 : 第 4 个人多少 ? 我比第 3 个人大 2 岁.
     * 问 : 第 3 个人多少 ? 我比第 2 个人大 2 岁.
     * 问 : 第 2 个人多少 ? 我比第 1 个人大 2 岁.
     * 问 : 第 1 个人多少 ? 我 10 岁.
     *
     * @return n 第几个人
     */
    public static int age(int n) {
        if (n == 1) {
            return 10;
        } else {
            return age(n - 1) + 2;
        }
    }
}
