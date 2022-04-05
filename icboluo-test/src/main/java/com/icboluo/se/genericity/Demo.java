package com.icboluo.se.genericity;


import java.util.*;

/**
 * 泛型
 * E(Element) T(Type) K(key) V(value) R(return)
 *
 * @author icboluo
 * @since 2020-08-13 12:34
 */
class Demo {
    public static void main(String[] args) {

        Collection<Person> persons = new ArrayList<>();
        Collections.addAll(persons,
                new Person("张三", 18),
                new Person("李四", 19),
                new Person("王五", 20)
        );
        Set<Animal> animals = Set.of(
                new Animal("哮天犬1号", 100),
                new Animal("哮天犬2号", 200),
                new Animal("哮天犬3号", 300)
        );

        List<Dog> dogs = List.of(
                new Dog("哮天犬1号", 100),
                new Dog("哮天犬2号", 200),
                new Dog("哮天犬3号", 300));


        printObjectCollection(persons);
        printAnimalCollection(dogs);
    }

    /**
     * ? extends Animal 我不知道集合元素是什么类型, 但是我知道, 这个类型继承自 Animal.
     * 泛型上限 : 限制了顶层的父类类型.
     *
     * @param list 单列集合
     */
    private static void printAnimalCollection(Collection<? extends Animal> list) {
        for (Animal animal : list) {
            System.out.println("animal = " + animal);
        }
    }

    /**
     * ? extends Object 我不知道集合元素是什么类型, 但是我知道, 这个类型继承自 Object
     *
     * @param list 容器
     */
    private static void printObjectCollection(Collection<?> list) {
        for (Object o : list) {
            System.out.println("o = " + o);
        }
    }
}
