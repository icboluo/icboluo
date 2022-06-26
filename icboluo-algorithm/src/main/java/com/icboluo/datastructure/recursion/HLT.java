package com.icboluo.datastructure.recursion;

/**
 * 从左到右有A、B、C三根柱子，其中A柱子上面有从小叠到大的n个圆盘，现要求将A柱子上的圆盘移到C柱子上去，期间只有一个原则：
 * 一次只能移到一个盘子且大盘子不能在小盘子上面，求移动的步骤和移动的次数
 *
 * @author icboluo
 * @since 2022-06-26 16:57
 */
public class HLT {

    /**
     * 将a盘上的所有移动到c盘
     *
     * @param num 3
     * @param a   a盘
     * @param b   b盘
     * @param c   盘
     */
    private void hlt(int num, char a, char b, char c) {
        if (num == 1) {
            System.out.println("第1个盘从" + a + "->" + c);
            return;
        }
        // 将a盘n-1个元素移动到b盘
        hlt(num - 1, a, c, b);
        System.out.println("第" + num + "个盘从" + a + "->" + c);
        hlt(num - 1, b, a, c);
    }

    public static void main(String[] args) {
        HLT cla = new HLT();
        cla.hlt(3, 'A', 'B', 'C');
    }
}
