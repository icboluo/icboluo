package com.icboluo.se.nonameclass;

import lombok.AllArgsConstructor;

/**
 * @author icboluo
 * @date 2020-08-11 10:44
 */
class People {
    private String name;

    /**
     * 外部类要访问内部类的成员内容必须先创建成员内部类的对象，再进行访问
     */
    private void print() {
        Student student = new Student();
        System.out.println(student.name);
    }

    /**
     * 成员内部类可以无条件访问外部类的所有成员属性和成员方法（包括private成员和静态成员）
     */
    class Student {
        private String name;

        private void print() {
//            外部类成员变量成员方法的访问要通过 类名.this 去访问
            System.out.println("内部类student的方法执行了" + People.this.name);
        }
    }

    /**
     * 局部内部类
     *
     * @return 外部类
     */
    public People printWoman() {
        class Woman extends People {
            String name;
        }
        return new Woman();
    }
    static class Inner {
        public Inner() {

        }
    }
}
