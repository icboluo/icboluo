package com.icboluo.designpattern.create.factory.simplefactory;

import com.icboluo.util.IcBoLuoException;

/**
 * @author icboluo
 * @date 2020/10/21 19:56
 */
public class Factory {
    public Pizza create(char c) {
        if (c == 'a') {
            return new APizza();
        } else if (c == 'b') {
            return new BPizza();
        } else {
            throw new IcBoLuoException("no this pizza");
        }
    }
}
