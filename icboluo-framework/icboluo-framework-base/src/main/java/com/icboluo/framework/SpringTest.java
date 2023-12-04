package com.icboluo.framework;

import org.junit.jupiter.api.Test;
import org.springframework.util.*;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author icboluo
 * @since 2023-01-08 0:16
 */
public class SpringTest {
    @Test
    public void classUtils() {
        // 获取对象的所有接口
        Class<?>[] allInterfaces = ClassUtils.getAllInterfaces(new LinkedList<>());
        // 获取类所在的包名
        String packageName = ClassUtils.getPackageName(LinkedList.class);
        // 判断某个类是否是内部类
        boolean innerClass = ClassUtils.isInnerClass(LinkedList.class);
        // 判断对象是否为代理对象
        boolean cglibProxy = ClassUtils.isCglibProxy(new LinkedList<>());
        List<String> list = new ArrayList<>();
        // 返回集合中的元素类型，如果集合.isEmpty | 拥有多种元素类型，返回的结果为null
        System.out.println(CollectionUtils.findCommonElementType(list));
    }

    @Test
    public void http() {
        // HttpStatus Spring包下也有
    }

    @Test
    public void reflection() {
        Method add = ReflectionUtils.findMethod(LinkedList.class, "add");
        Field field = ReflectionUtils.findField(LinkedList.class, "field");
    }

    @Test
    public void stream() throws IOException {
        // 是一个比较好用的api
        StreamUtils.copy(new byte[]{}, null);
        byte[] bytes = StreamUtils.copyToByteArray(null);
    }

    @Test
    public void fileCopyUtils() throws IOException {
        byte[] bytes = FileCopyUtils.copyToByteArray(new File(""));
        String s = FileCopyUtils.copyToString(null);
        // 还有好多输入输出的copy方法
    }

    @Test
    public void IoUtils() {
        // IOUtils
    }
}
