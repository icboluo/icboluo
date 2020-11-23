package com.icboluo.designpattern.template.improve;

/**
 * @author icboluo
 * @date 2020/11/15 21:04
 */
public class PureSoyaMilk extends SoyaMilk {
    @Override
    void addCondiments() {

    }

    @Override
    boolean customerWantCondiments() {
        return false;
    }
}
