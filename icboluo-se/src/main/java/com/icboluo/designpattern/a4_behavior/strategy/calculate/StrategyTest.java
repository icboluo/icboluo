package com.icboluo.designpattern.a4_behavior.strategy.calculate;

/**
 * 策略模式
 *
 * @author icboluo
 */
class StrategyTest {
    public static void main(String[] args) {
        String exp = "2*8";
        ICalculator cal = new Multiply();
        int result = cal.calculate(exp);
        System.out.println(result);
    }
}
