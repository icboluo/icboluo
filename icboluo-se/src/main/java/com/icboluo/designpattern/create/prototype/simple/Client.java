package com.icboluo.designpattern.create.prototype.simple;

import lombok.SneakyThrows;

/**
 * @author icboluo
 * @since 2023-03-02 22:28
 */
class Client {
    public static void main(String[] args) {
        shallowCopy();
        test02();
        test03();
    }


    /**
     * 浅拷贝
     */
    @SneakyThrows
    private static void shallowCopy() {
        SheepShallowCopy sheep = new SheepShallowCopy("tom", 12, "red");
        SheepShallowCopy clone = sheep.clone();
        System.out.println(sheep);
        System.out.println(clone);
    }

    @SneakyThrows
    private static void test02() {
        SheepFriend sheepFriend = new SheepFriend("xxx", 77);
        SheepDeepCopy sheepDeepCopy = new SheepDeepCopy();
        sheepDeepCopy.setName("yyy");
        sheepDeepCopy.setFriend(sheepFriend);
        SheepDeepCopy clone = sheepDeepCopy.clone();
        // 为什么这里要加上一个才可以实现hash值不同 TODO
        sheepFriend.setName("zzz");
        System.out.println(sheepDeepCopy.getFriend().hashCode());
        System.out.println(clone.getFriend().hashCode());
    }

    @SneakyThrows
    private static void test03() {
        SheepFriend sheepFriend = new SheepFriend("xxx", 77);
        SheepDeepCopy sheepDeepCopy = new SheepDeepCopy();
        sheepDeepCopy.setName("yyy");
        sheepDeepCopy.setFriend(sheepFriend);
        SheepDeepCopy clone = sheepDeepCopy.deepCopy();
        sheepFriend.setName("zzz");
        System.out.println(sheepDeepCopy.getFriend().hashCode());
        System.out.println(clone.getFriend().hashCode());
    }
}
