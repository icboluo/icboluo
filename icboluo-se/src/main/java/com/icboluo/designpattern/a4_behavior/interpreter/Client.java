package com.icboluo.designpattern.a4_behavior.interpreter;

import java.util.HashMap;

/**
 * @author icboluo
 * @since 2020/11/22 16:59
 */
public class Client {
    public static void main(String[] args) {
        String expStr = "a+b";
        HashMap<String, Integer> var = new HashMap<>();
        var.put("a", 10);
        var.put("b", 20);
        Calculator calculator = new Calculator(expStr);
        int run = calculator.run(var);
        System.out.println("run = " + run);

    }
}
