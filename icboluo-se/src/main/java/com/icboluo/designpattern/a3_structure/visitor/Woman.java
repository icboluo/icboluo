package com.icboluo.designpattern.a3_structure.visitor;

/**
 * @author icboluo
 * @since 2023-03-02 22:09
 */
class Woman extends Person {
    @Override
    public void accept(Action action) {
        action.getWomanResult(this);
    }
}
