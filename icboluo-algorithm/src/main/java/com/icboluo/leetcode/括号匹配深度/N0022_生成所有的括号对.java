package com.icboluo.leetcode.括号匹配深度;

import java.util.ArrayList;
import java.util.List;

/**
 * @author icboluo
 * @since 2020-10-09 20:53
 */
class N0022_生成所有的括号对 {
    public static void main(String[] args) {
        N0022_生成所有的括号对 cla = new N0022_生成所有的括号对();
        List<String> res = cla.generateParenthesis(5);
        System.out.println("res = " + res);
    }

    /**
     * 递归
     * 结束条件：让左右均没有括号或者单方面递归成负值
     * 结果会是先全部左括号，再全部右括号
     *
     * @param n
     */
    public List<String> generateParenthesis(int n) {
        List<String> list = new ArrayList<>();
        generateParenthesis1("", n, n, list);
        return list;
    }

    /**
     * @param str
     * @param left  左括号个数
     * @param right 右括号个数
     * @param list
     */
    private void generateParenthesis1(String str, int left, int right, List<String> list) {
        // 结束单方面递归到负值
        // left>right是 )( 的情况
        if (left < 0 || right < 0 || left > right) {
            return;
        }
        // 约束条件，没有左右条件；并且收集str
        if (left == 0 && right == 0) {
            list.add(str);
            return;
        }
        // 不是回溯先全左括号到全右括号（全右括号不合理，应该删除
        // 其实，左括号必须在右括号左边，所以不能出现递归右括号的场景，也就是right不能先减
        generateParenthesis1(str + "(", left - 1, right, list);
        generateParenthesis1(str + ")", left, right - 1, list);
    }

    /**
     * TODO 暴力、构造解法
     *
     * @param n
     */
    private void generateParenthesis2(int n) {
    }
}
