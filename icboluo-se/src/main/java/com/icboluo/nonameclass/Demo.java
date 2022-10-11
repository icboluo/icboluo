package com.icboluo.nonameclass;

/**
 * 什么时候使用匿名内部类？？
 * 当我们需要一个父类的对象，而这个对象只被使用一次，这个对象的类没必须特意创建.java源文件，此时就直接使用匿名内部类即可
 *
 * @author icboluo
 * @since 2020-08-11 10:53
 */
class Demo {
    public static void main(String[] args) {
        // 成员内部类可以通过这种方式创建对象
        People people = new People();
        People.Student student = people.new Student();
        // 匿名内部类是说内部的类是没有名字的，就是说相当于一个接口的实现类是没有名字的
        new PrintInterface() {
            @Override
            public void print() {
                System.out.println("匿名内部类的使用");
            }
        }.print();
        // 静态内部类 创建对象
        People.Inner inner = new People.Inner();
    }
}
