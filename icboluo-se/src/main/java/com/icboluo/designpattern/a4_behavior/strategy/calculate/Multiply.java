package com.icboluo.designpattern.a4_behavior.strategy.calculate;

class Multiply extends AbstractCalculator implements ICalculator {

    @Override
    public int calculate(String exp) {
        int[] arrayInt = split(exp, "\\*");
        return arrayInt[0] * arrayInt[1];
    }
}
