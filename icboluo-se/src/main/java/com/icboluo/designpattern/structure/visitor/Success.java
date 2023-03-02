package com.icboluo.designpattern.structure.visitor;

/**
 * @author icboluo
 * @since 2023-03-02 22:10
 */
class Success extends Action {
    @Override
    public void getManResult(Man man) {
        System.out.println("男评委评价成功");
    }

    @Override
    public void getWomanResult(Woman woman) {
        System.out.println("女评委评价成功");
    }
}
