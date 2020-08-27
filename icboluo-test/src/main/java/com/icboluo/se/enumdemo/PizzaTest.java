package com.icboluo.se.enumdemo;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * @author icboluo
 * @date 2020/5/28 17:18
 */
public class PizzaTest {
    @Test
    public void test1() {
        Pizza2 testPz = new Pizza2();
        testPz.setStatus(Pizza2.PizzaStatus.READY);
        boolean deliverable = testPz.isDeliverable();
        System.out.println("deliverable = " + deliverable);
    }
}

