package com.icboluo.spring.accountservlet;

public class HelloDaoImpl implements IHelloDao {
    @Override
    public void sayHello() {
        System.out.println("hello");
    }
}
