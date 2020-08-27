package com.icboluo.se.container;

import org.junit.Test;

import java.util.LinkedHashSet;
import java.util.Scanner;

/**
 * @author lp
 */
public class LinkedHashSetTest {

    /**
     * 去掉其中重复字符，打印出不同的那些字符，必须保证顺序。打印结果为：abcd。
     */
    @Test
    public void test1() {
        String s = "aaaabbbcccddd";
        LinkedHashSet<Character> lhs = new LinkedHashSet<>();
        char[] chars = s.toCharArray();
        for (char c : chars) {
            lhs.add(c);
        }
        for (char c : lhs) {
            System.out.print(c);
        }
    }
}
