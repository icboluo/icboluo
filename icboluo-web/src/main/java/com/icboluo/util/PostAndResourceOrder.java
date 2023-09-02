package com.icboluo.util;

import com.icboluo.controller.Demo;
import com.icboluo.object.StatusStudent;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author icboluo
 * @since 2023-08-25 20:10
 */
@Component
@Slf4j
public class PostAndResourceOrder {

    private static String role;

    /**
     * 成员方法给静态变量赋值
     *
     * @param demo 这个参数只是模拟DI而已，找一个spring容器中的赋值给静态变量即可
     */
    @Resource
    private void setRole(Demo demo) {
        role = "member di set value for static field, first execution";
        log.warn(role);
    }

    /**
     * @see jakarta.annotation.PreDestroy 和PostConstruct基本一致，但是我没有用过
     */
    @PostConstruct
    private void setRole() {
        role = "post construct set value for static field, second execution";
        log.warn(role);
    }

    /**
     * 类上需要增加 @Configuration @Component 等注解才可以让bean的内容被别的地方注入或者 @Resource
     *
     * @return 含有状态属性的学生
     */
    @Bean
    public StatusStudent student() {
        log.warn("student config bean is init, four execution");
        return new StatusStudent(18, "xiao ming");
    }
}
