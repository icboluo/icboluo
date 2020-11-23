package com.icboluo.designpattern.principle.inversion3;

/**
 * @author icboluo
 * @date 2020-09-02 16:05
 */
 class Person {
    public void receive(Email email) {
        System.out.println(email.getInfo());
    }
}
