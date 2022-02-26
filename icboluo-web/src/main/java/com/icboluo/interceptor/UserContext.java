package com.icboluo.interceptor;

import com.icboluo.controller.ResultController;
import com.icboluo.util.IcBoLuoException;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * @author icboluo
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Component
@Slf4j
public class UserContext {
    private static final ThreadLocal<AdminUser> USER_CONTEXT = new ThreadLocal<>();

    private static String role;

    @PostConstruct
    private void setRole() {
        role = "post construct set value for static field, second execution";
        log.warn(role);
    }

    /**
     * 成员方法给静态变量赋值
     *
     * @param resultController 这个参数只是模拟DI而已，找一个spring容器中的赋值给静态变量即可
     */
    @Resource
    private void setRole(ResultController resultController) {
        role = "member di set value for static field, first execution";
        log.warn(role);
    }


    @AllArgsConstructor
    private static class AdminUser {
        String userCode;
    }

    public static String userCode() {
        validate();
        return USER_CONTEXT.get().userCode;
    }

    private static void validate() {
        boolean isNull = ObjectUtils.isEmpty(USER_CONTEXT.get()) || ObjectUtils.isArray(USER_CONTEXT.get().userCode);
        if (isNull) {
            throw new IcBoLuoException("No userCode in Context");
        }
    }

    public static void set(String userCode) {
        AdminUser adminUser = new AdminUser(userCode);
        USER_CONTEXT.set(adminUser);
    }

    public static void remove() {
        USER_CONTEXT.remove();
    }
}
