package com.icboluo;

import com.icboluo.service.OrderService;
import com.icboluo.spring.ApplicationContext;

/**
 * @author icboluo
 * @since 2022-08-09 23:45
 */
public class Test {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ApplicationContext(AppConfig.class);
        Object bean = applicationContext.getBean("orderService");
        System.out.println(bean);
        System.out.println(applicationContext.getBean("orderService"));

        OrderService orderService = (OrderService) applicationContext.getBean("orderService");
        orderService.test();
    }
}
