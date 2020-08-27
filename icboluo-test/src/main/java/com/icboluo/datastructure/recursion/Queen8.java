package com.icboluo.datastructure.recursion;

/**
 * @author icboluo
 */
class Queen8 {
    int max = 8;
    /**
     * 结果集
     */
    int[] array = new int[max];

    static int count = 0;

    public static void main(String[] args) {
        Queen8 queen8 = new Queen8();
        queen8.check(0);
        System.out.printf("一共有 %d 种解法", count);
    }

    /**
     * 回溯会产生所有的结果，因为是check递归，所以每次进入到check中都有  for (int i = 0; i < max; i++) {
     * 会将摆放位置后移再次运算
     *
     * @param n
     */
    private void check(int n) {
        if (n == max) {
            print();
            return;
        }
        for (int i = 0; i < max; i++) {
            array[n] = i;
            boolean judge = judge(n);
            if (judge) {
                check(n + 1);
            }
        }
    }

    /**
     * @param n 表示放置第n个皇后
     * @return 是否和前面的皇后冲突
     */
    private boolean judge(int n) {
        for (int i = 0; i < n; i++) {
            //在同一列或者同一斜线
            boolean b = array[i] == array[n] || Math.abs(n - i) == Math.abs(array[n] - array[i]);
            if (b) {
                return false;
            }
        }
        return true;
    }

    private void print() {
        for (int value : array) {
            System.out.print(value + " ");
        }
        count++;
        System.out.println();
    }
}
