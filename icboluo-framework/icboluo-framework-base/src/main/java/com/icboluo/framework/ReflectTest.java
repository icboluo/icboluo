package com.icboluo.framework;

import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Properties;

/**
 * 反射
 *
 * @author icboluo
 * @since 2024-12-31 18:20
 */
@Slf4j
@Getter
@ToString
class ReflectTest {
    public String name;

    public ReflectTest() {
    }


    /**
     * 获取类
     *
     * @throws ClassNotFoundException 异常
     */
    @Test
    public void getCla() throws ClassNotFoundException {
        // 1.多用于配置文件
        Class<?> clazz1 = Class.forName("com.icboluo.framework.ReflectTest");
        System.out.println("clazz1 = " + clazz1);
        // 2.多用于参数传递
        Class<ReflectTest> clazz2 = ReflectTest.class;
        System.out.println("clazz2 = " + clazz2);
        // 3.多用于对象获取字节码文件
        ReflectTest person = new ReflectTest();
        Class<? extends ReflectTest> clazz3 = person.getClass();
        System.out.println("clazz3 = " + clazz3);
        // 他们3个都是相等的，同一个字节码文件在一次程序的运行过程中，只会被加载一次
        System.out.println(clazz1 == clazz2);
        // 获取类名
        log.info("class name: {}", clazz1.getName());
    }

    /**
     * 获取构造方法
     *
     * @throws NoSuchMethodException 异常
     */
    @Test
    public void getConstructor() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<ReflectTest> cla = ReflectTest.class;
        Constructor<ReflectTest> declaredConstructor = cla.getDeclaredConstructor();
        Constructor<?>[] declaredConstructors = cla.getDeclaredConstructors();

        try {
            Constructor<ReflectTest> constructor = cla.getConstructor();
            Constructor<?>[] constructors = cla.getConstructors();
        } catch (NoSuchMethodException e) {
            log.error("access error");
        } catch (SecurityException e) {
            throw new RuntimeException(e);
        }
        log.info("constructor : {}", declaredConstructor);

        // 获取实例，可以填入参数类型 String.class
        Constructor<ReflectTest> nameConstructor = cla.getDeclaredConstructor();
        ReflectTest inst = nameConstructor.newInstance();
        log.info("constructor method test : {}", inst);
    }

    /**
     * 获取成员变量
     *
     * @throws NoSuchFieldException   异常
     * @throws IllegalAccessException 字段获取异常
     */
    @Test
    public void getField() throws NoSuchFieldException, IllegalAccessException {
        Class<ReflectTest> cla = ReflectTest.class;
        // 只能获得public修饰的成员变量
        Field name1 = cla.getField("name");
        Field[] fields = cla.getFields();

        Field name2 = cla.getDeclaredField("name");
        Field[] declaredFields = cla.getDeclaredFields();
        log.info("field : {}", name1);

        // 暴力反射,忽略权限修饰符
        var inst = new ReflectTest();
        inst.name = "test demo";
        name1.setAccessible(true);
        String o = (String) name1.get(inst);
        log.info("instance object field name is: {}", o);
    }

    @Test
    public void getMethod() throws NoSuchMethodException {
        Class<ReflectTest> cla = ReflectTest.class;
        Method method = cla.getMethod("getName");
        Method[] methods = cla.getMethods();

        Method declaredMethod = cla.getDeclaredMethod("getName");
        Method[] declaredMethods = cla.getDeclaredMethods();
        log.info("method : {}", method);
    }

    /**
     * 从配置文件中获取对象以及方法
     */
    @Test
    public void findObjectAndMethodByProperties() throws IOException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Properties pro = new Properties();
        // 获取配置文件并加载到properties中
        ClassLoader classLoader = ReflectTest.class.getClassLoader();
        InputStream is = classLoader.getResourceAsStream("application.yml");
        pro.load(is);
        // 获取配置文件中的属性
        String className = pro.getProperty("className");
        String methodName = pro.getProperty("methodName");
        // 加载该类进内存
        Class<?> clazz = Class.forName(className);
        // 创建对象调用方法
        Object obj = clazz.getDeclaredConstructor().newInstance();
        Method method = clazz.getMethod(methodName);
        method.invoke(obj);
    }
}
