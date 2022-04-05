package com.icboluo.designpattern.create.prototype.sheep;

/**
 * 原形模式就是实现Cloneable接口，是浅拷贝
 *
 * @author icboluo
 * @since 2020/10/27 19:56
 */
public class Client {
    public static void main(String[] args) {
        Sheep sheep = new Sheep("tom", 1, "白色");
        sheep.friend = new Sheep("jack", 2, "黑色");
        Sheep sheep1 = (Sheep) sheep.clone();
        System.out.println(sheep.friend==sheep1.friend);
    }
}
