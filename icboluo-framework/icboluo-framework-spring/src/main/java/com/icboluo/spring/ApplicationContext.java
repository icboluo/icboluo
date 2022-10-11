package com.icboluo.spring;

import lombok.SneakyThrows;

import java.io.File;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author icboluo
 * @since 2022-08-09 23:45
 */
public class ApplicationContext {

    private Class<?> configClass;

    private ConcurrentHashMap<String, Object> singletonObjectMap = new ConcurrentHashMap<>();
    private ConcurrentHashMap<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

    @SneakyThrows
    public ApplicationContext(Class<?> configClass) {
        this.configClass = configClass;

        ComponentScan componentScan = configClass.getDeclaredAnnotation(ComponentScan.class);
        // com.icboluo
        String dirPath = componentScan.value();

        // 扫描
        scan(dirPath);

        // 一次性初始化单例池
        for (Map.Entry<String, BeanDefinition> entry : beanDefinitionMap.entrySet()) {
            String beanName = entry.getKey();
            BeanDefinition beanDefinition = entry.getValue();
            Object bean = createBean(beanName, beanDefinition);
            singletonObjectMap.put(beanName, bean);
        }
    }

    private void scan(String dirPath) throws ClassNotFoundException {
        // BootStrap->jre/lib
        // Ext------->jre/ext/lib
        // App------->classpath
        ClassLoader classLoader = ApplicationContext.class.getClassLoader();
        dirPath = dirPath.replace(".", "/");
        // com/icboluo
        URL resource = classLoader.getResource(dirPath);
        File file = new File(resource.getFile());
        if (!file.isDirectory()) {
            return;
        }
        for (File f : file.listFiles()) {
            // D:\IdeaProjects\icboluo\icboluo-common\icboluo-base\target\classes\com\icboluo\service\impl\OrderService.class
            String filePath = f.getAbsolutePath();
            filePath = filePath.substring(filePath.indexOf("com\\icboluo"), filePath.indexOf(".class"));
            filePath = filePath.replace("\\", ".");
            // com.icboluo.service.impl.OrderService
            Class<?> clazz = classLoader.loadClass(filePath);
            if (!clazz.isAnnotationPresent(Component.class)) {
                continue;
            }
            Component component = clazz.getDeclaredAnnotation(Component.class);
            String beanName = component.value();
            BeanDefinition beanDefinition = new BeanDefinition();
            beanDefinition.setClazz(clazz);
            if (clazz.isAnnotationPresent(Scope.class)) {
                Scope scope = clazz.getDeclaredAnnotation(Scope.class);
                beanDefinition.setScope(scope.value());
            } else {
                beanDefinition.setScope("singleton");
            }
            beanDefinitionMap.put(beanName, beanDefinition);
        }
    }

    public Object getBean(String beanName) {
        if (!beanDefinitionMap.containsKey(beanName)) {
            return null;
        }
        BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
        if ("prototype".equals(beanDefinition.getScope())) {
            return createBean(beanName, beanDefinition);
        }
        if ("singleton".equals(beanDefinition.getScope())) {
            if (!singletonObjectMap.containsKey(beanName)) {
                Object bean = createBean(beanName, beanDefinition);
                singletonObjectMap.put(beanName, bean);
            }
            return singletonObjectMap.get(beanName);
        }
        return null;
    }

    @SneakyThrows
    private Object createBean(String beanName, BeanDefinition beanDefinition) {
        Class<?> clazz = beanDefinition.getClazz();
        Object instance = clazz.getDeclaredConstructor().newInstance();
        // 属性赋值
        for (Field field : clazz.getDeclaredFields()) {
            if (!field.isAnnotationPresent(Autowired.class)) {
                continue;
            }
            Object bean = getBean(field.getName());
            field.setAccessible(true);
            field.set(instance, bean);
        }
        // aware回调
        if (instance instanceof BeanNameAware beanNameAware) {
            beanNameAware.setBeanName(beanName);
        }
        // 初始化
        if (instance instanceof InitializingBean initializingBean) {
            initializingBean.afterPropertiesSet();
        }

        return instance;
    }
}
