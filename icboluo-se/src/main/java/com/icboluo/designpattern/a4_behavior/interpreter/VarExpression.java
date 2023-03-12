package com.icboluo.designpattern.a4_behavior.interpreter;

import lombok.AllArgsConstructor;

import java.util.HashMap;

/**
 * @author icboluo
 * @since 2020/11/22 17:05
 */
@AllArgsConstructor
public class VarExpression extends Expression {
    private String key;

    /**
     * 根据变量的名称返回对应值
     *
     * @param var k：公式的参数 v：具体值
     * @return
     */
    @Override
    public int interpreter(HashMap<String, Integer> var) {
        return var.get(key);
    }
}
