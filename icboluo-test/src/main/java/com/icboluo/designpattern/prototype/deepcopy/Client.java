package com.icboluo.designpattern.prototype.deepcopy;


import org.junit.jupiter.api.Test;

/**
 * @author icboluo
 * @date 2020/10/27 19:57
 */
public class Client {
    public static void main(String[] args) {
        Sheep sheep = new Sheep("tom", 1, "白色");
        sheep.friend = new Friend( 2, "jack");
        Sheep sheep1 = (Sheep) sheep.clone();
        System.out.println(sheep.friend == sheep1.friend);
    }

    @Test
    public void test() {
        Sheep sheep = new Sheep("tom", 1, "白色");
        sheep.friend = new Friend( 2, "jack");
        Sheep sheep1 = (Sheep) sheep.deepClone();
        System.out.println(sheep.friend == sheep1.friend);
    }

}
