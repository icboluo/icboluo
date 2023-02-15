package com.icboluo.service;

import com.icboluo.spring.*;

/**
 * @author icboluo
 * @since 2022-08-09 23:49
 */
@Component("orderService")
@Scope("singleton")
public class OrderService implements BeanNameAware, InitializingBean {

    @Autowired
    private CommonServiceImpl commonServiceImpl;

    private String beanName;


    @Override
    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    @Override
    public void afterPropertiesSet() {
        System.out.println("OrderService 被初始化了");
    }

    public void test() {
        System.out.println(commonServiceImpl);
        System.out.println(beanName);
    }


}
