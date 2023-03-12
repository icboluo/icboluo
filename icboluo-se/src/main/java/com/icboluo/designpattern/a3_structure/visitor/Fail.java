package com.icboluo.designpattern.a3_structure.visitor;

/**
 * @author icboluo
 * @since 2023-03-02 22:10
 */
class Fail extends Action {
    @Override
    public void getManResult(Man man) {
        System.out.println("男评委评价失败");
    }

    @Override
    public void getWomanResult(Woman woman) {
        System.out.println("女评委评价失败");
    }
}
