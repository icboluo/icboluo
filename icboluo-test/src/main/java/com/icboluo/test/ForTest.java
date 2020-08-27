package com.icboluo.test;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

/**
 * @author icboluo
 * @date 2020/6/16 10:17
 */
public class ForTest {

    /**
     * 迭代器的使用
     */
    @Test
    public void test1() {
        HashMap<String, String> hm = new HashMap<>();
        hm.put("黄晓明", "Baby");
        hm.put("邓超", "孙俪");
        hm.put("李晨", "范冰冰");
        hm.put("大黑牛", "范冰冰");

        Collection<String> values = hm.values();
//        第一次获取迭代器时，光标位于第0个元素前
        Iterator<String> it = values.iterator();
//        取出下一个元素光标并向后移一位
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }

    /**
     * 使用场景 : 集合的快速遍历. 只能遍历, 在遍历的同时, 不能对集合元素进行 `增删改` 操作.
     * foreach 底层就是 `迭代器`. 在遍历的同时, 不能对集合元素进行 `增删改` 操作.
     * for i 循环不涉及迭代器. 因此可以在遍历的同时, 删除元素.
     */
    @Test
    public void test2() {
        Collection<String> list = new ArrayList<>();
        list.add("刘德华");
        list.add("李冰冰");
        list.add("范冰冰");
        list.add("黎明");
        list.add("郭富城");

//        for (String name : list) {
//            if (name.contains("冰冰")) {
//                list.remove(name);
//            }
//        }
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            String name = it.next();
            if (name.contains("冰冰")) {
                //it.remove();
                list.remove(name);
            }
        }
        System.out.println(list);
    }

    @Test
    public void test3() {
        Collection<String> list = new ArrayList<>();
        list.add("张三");
        list.add("李四");
        list.add("王五");

        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            String name = it.next();
            if (name.contains("四")) {
                list.remove(name);
            }
        }
        System.out.println(list);
    }
}
