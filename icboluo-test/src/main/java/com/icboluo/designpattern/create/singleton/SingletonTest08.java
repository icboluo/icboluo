package com.icboluo.designpattern.create.singleton;

/**
 * 枚举 也可以防止反序列化重写创建新的对象 推荐使用
 *
 * @author icboluo
 * @date 2020/10/17 18:20
 */
public enum SingletonTest08 {
    /**
     * 实例
     */
    instance;

    public void sayOk() {
        System.out.println("ok");
    }
}
