package com.icboluo.designpattern.create.factory.abstractfactory.order;

import com.icboluo.designpattern.create.factory.abstractfactory.pizza.Pizza;

/**
 * 一个抽象工厂模式的抽象层（接口）
 *
 * @author icboluo
 * @date 2020/12/5 18:51
 */
public interface AbsFactory {

    Pizza creatPizza(String orderType);
}
