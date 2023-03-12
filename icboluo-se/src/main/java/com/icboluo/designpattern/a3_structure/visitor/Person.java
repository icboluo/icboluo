package com.icboluo.designpattern.a3_structure.visitor;

/**
 * @author icboluo
 * @since 2023-03-02 22:09
 */
abstract class Person {
    public abstract void accept(Action action);
}
