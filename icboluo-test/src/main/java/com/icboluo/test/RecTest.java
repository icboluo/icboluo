package com.icboluo.test;

/**
 * @author icboluo
 * @date 2021-26-10 12:26
 */
public class RecTest {
    public static void main(String[] args) {
        Rec rec = new Rec(18, "小王");
        Integer id = rec.id();
        System.out.println("id = " + id);
    }
}
