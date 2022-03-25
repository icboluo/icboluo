package com.icboluo.leetcode;

/**
 * @author icboluo
 * @date 2022-03-25 19:52
 */
public class N0009_回文数 {

    private void hws(int a) {
        new StringBuilder(a).reverse().compareTo(new StringBuilder(a));
    }
}
