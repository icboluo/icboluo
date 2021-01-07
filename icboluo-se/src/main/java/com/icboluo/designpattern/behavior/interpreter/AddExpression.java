package com.icboluo.designpattern.behavior.interpreter;

import java.util.HashMap;

/**
 * @author icboluo
 * @date 2020/11/22 17:08
 */
public class AddExpression extends SymbolExpression {
    public AddExpression(Expression left, Expression right) {
        super(left, right);
    }

    /**
     * 返回相加的结果
     *
     * @param var k：公式的参数 v：具体值
     * @return
     */
    @Override
    public int interpreter(HashMap<String, Integer> var) {
        return super.left.interpreter(var) + super.right.interpreter(var);
    }
}
