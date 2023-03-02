package com.icboluo.designpattern.structure.decorator;

import lombok.Data;

/**
 * 饮料
 *
 * @author icboluo
 * @since 2023-03-02 21:14
 */
@Data
abstract class Drink {
    /**
     * 描述
     */
    private String description;

    /**
     * 价格
     */
    private Integer price = 0;

    /**
     * 花费
     *
     * @return 花费的价钱
     */
    public abstract Integer cost();
}
