package com.icboluo.leetcode.after_2000;

import com.icboluo.common.ListNode;

/**
 * @author icboluo
 * @since 2023-03-29 21:03
 */
class N2074_2217_偶数长度组中的反向节点 {
    /**
     * 偶数反转 FIXME ERROR
     *
     * @param head 5,2,6,3,9,1,7,3,8,4
     * @return
     */
    public ListNode reverseEvenLengthGroups(ListNode head) {
        int i = 0;
        // 挺麻烦的代码，需要慢慢梳理
        ListNode cur = head;
        ListNode temp = null;
        while (cur != null) {
            i++;
            ListNode pre = null;
            ListNode tempHead = cur;
            for (int j = 0; j < i; j++) {
                if (i % 2 == 0) {
                    if (cur != null) {
                        ListNode nextNode = cur.next;
                        cur.next = pre;
                        pre = cur;
                        cur = nextNode;
                    }
                    temp.next = pre;
                    tempHead.next = cur;
                } else {
                    if (cur != null) {
                        temp = cur;
                        cur = cur.next;
                    }
                }
            }
        }
        return head;
    }

    /**
     * 2217 查找固定长度的回文 hard to understand
     * 101-999 对于三位数，一共有90个回文数，是根据10^2次幂大致计算的
     *
     * @param queries   第i个回文数
     * @param intLength
     * @return
     */
    public long[] kthPalindrome(int[] queries, int intLength) {
        long[] res = new long[queries.length];
        // 有效长度/一半长度
        int halfLength = (intLength + 1) >> 1;
        // 10 前2位数字的最小值为10
        long small = (long) (Math.pow(10, halfLength - 1));
        // 99 前2位数的最大值为99
        long large = (long) (Math.pow(10, halfLength) - 1);
        int i = 0;
        for (int num : queries) {
            // 2位数的中间值的个数为99-10+1=90个
            if (num <= large - small + 1) {
                String left = small + (num - 1) + "";
                String right = new StringBuilder(left).reverse().toString();
                // 如果数字为奇数，则右串不包含首位
                res[i++] = Long.parseLong(left + right.substring(intLength % 2));
            } else {
                res[i++] = -1;
            }
        }
        return res;
    }

    /**
     * 2002 两个回文子序列长度的最大乘积 mid problem TODO
     * 0564 找到最近的回文 it`s hard
     * 0877 石头游戏 这是一个序列问题，而且不是很好解决
     *
     * @param s
     * @return
     */
    public int maxProduct(String s) {
        return -1;
    }
}
