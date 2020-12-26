package com.icboluo.designpattern.structure.flyweight;

import lombok.AllArgsConstructor;

/**
 * @author icboluo
 * @date 2020/12/26 16:51
 */
@AllArgsConstructor
public class ConcreteWebSite extends WebSite {
    /**
     * 网站发布的类型（这个是共享的部分，属于内部状态）
     * 存储在享元对象内部且不会随环境的改变而改变
     */
    private String type = "";

    /**
     * 这个user是外部状态
     * 指对象得以依赖的标记，随环境改变而改变，不可共享的状态
     *
     * @param user
     */
    @Override
    public void use(User user) {
        System.out.println("网站的发布形式为：" + type + ",使用者为：" + user);
    }
}
