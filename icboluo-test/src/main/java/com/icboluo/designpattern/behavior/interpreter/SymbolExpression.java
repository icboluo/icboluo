package com.icboluo.designpattern.behavior.interpreter;

import lombok.AllArgsConstructor;

import java.util.HashMap;

/**
 * @author icboluo
 * @date 2020/11/22 17:06
 */
@AllArgsConstructor
public class SymbolExpression extends Expression {

    protected Expression left;

    protected Expression right;

    /**
     * 让子类实现，自己默认空实现
     *
     * @param var k：公式的参数 v：具体值
     * @return
     */
    @Override
    public int interpreter(HashMap<String, Integer> var) {
        return 0;
    }
}
