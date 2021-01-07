package com.icboluo.designpattern.structure.bridge;

import lombok.AllArgsConstructor;

/**
 * @author icboluo
 * @date 2020/12/10 22:34
 */
@AllArgsConstructor
public abstract class Phone {
    /**
     * 组合品牌
     */
    private Brand brand;

    protected void open() {
        brand.open();
    }

    protected void close() {
        brand.close();
    }

    protected void call() {
        brand.call();
    }
}
