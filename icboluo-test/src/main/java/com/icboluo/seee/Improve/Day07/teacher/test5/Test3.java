package com.icboluo.seee.Improve.Day07.teacher.test5;

import java.util.ArrayList;
import java.util.Collections;

public class Test3 {
    public static void main(String[] args) {

        // 需求 : 对集合中的 Student 对象进行排序. 规则为: 按照年龄从小到大排列.
        ArrayList<Student> list = new ArrayList<>();

        list.add(new Student("张三", 18));
        list.add(new Student("李四", 16));
        list.add(new Student("王五", 20));
        list.add(new Student("赵六", 19));
        list.add(new Student("田七", 17));
        list.add(new Student("大八", 15));


        // Collections.sort(List, Comprator);
        // 方式一 : 匿名实现类
        /*Collections.sort(list, new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return o1.getAge() - o2.getAge();
            }
        });*/

        // 方式二 : Lambda 表达式
        /*
        1. 小括号 : 抽象方法的参数列表
        2. 箭头   : 分隔符
        3. 大括号 : 抽象方法的实现体.
         */
        // Collections.sort(list, (Student o1, Student o2) -> {return o2.getAge() - o1.getAge();});

        /*
        Lambda 表达式的省略规则 :
        1. 小括中的参数类型可以直接省略. 如果小括号中仅有一个参数, 小括号也可以省略.
        2. 大括号中, 不管是否有返回值, return 关键字都可以省略.
        3. 大括号中仅有一条语句, 大括号必须省略. 并且分号也需要省略.
         */
        Collections.sort(list, (o1, o2) -> o1.getAge() - o2.getAge());

        for (Student stu : list) {
            System.out.println(stu);
        }
    }
}
