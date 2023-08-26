package com.icboluo.interceptor;

import com.icboluo.util.IcBoLuoException;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;


/**
 * @author icboluo
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Component
@Slf4j
public class WebContext {
    private static final ThreadLocal<AdminUser> USER_CONTEXT = new ThreadLocal<>();

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
