package com.icboluo.designpattern.structure.visitor;

/**
 * @author icboluo
 * @since 2023-03-02 22:08
 */
abstract class Action {
    public abstract void getManResult(Man man);

    public abstract void getWomanResult(Woman woman);
}
