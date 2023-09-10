package com.icboluo.spring.util;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class BeanFactory {
    private static ResourceBundle rb = ResourceBundle.getBundle("beans");
    private static Map<String, Object> map = new HashMap<>();

    static {
        Enumeration<String> keys = rb.getKeys();
        while (keys.hasMoreElements()) {
            String key= keys.nextElement();
            String value = rb.getString(key);
            try {
                Object obj = Class.forName(value).newInstance();
                map.put(key, obj);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static Object getBean(String beanName) {
        return map.get(beanName);
    }
}
