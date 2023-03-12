package com.icboluo.designpattern.a2_create.factory.factorymethod.pizza;

import lombok.Setter;

/**
 * @author icboluo
 * @since 2020/10/21 19:54
 */
public abstract class Pizza {
    @Setter
    private String name;
    /**
     * 准备pizza
     */
    public abstract void prepare();
}
