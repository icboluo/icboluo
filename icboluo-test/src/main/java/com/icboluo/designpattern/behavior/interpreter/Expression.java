package com.icboluo.designpattern.interpreter;

import java.util.HashMap;

/**
 * 通过hashmap 获取到变量的值
 *
 * @author icboluo
 * @date 2020/11/22 17:02
 */
public abstract class Expression {
    /**
     *
     * @param var k：公式的参数 v：具体值
     * @return
     */
    public abstract int interpreter(HashMap<String, Integer> var);
}
