package com.icboluo.interceptor;

import com.icboluo.util.IcBoLuoException;
import lombok.AllArgsConstructor;
import org.springframework.util.ObjectUtils;

/**
 * @author icboluo
 */
public class UserContext {
    private static ThreadLocal<AdminUser> userContext = new ThreadLocal();

    @AllArgsConstructor
    private static class AdminUser {
        String userCode;
    }

    public static String getUserCode() {
        boolean isNull = userContext == null
                || ObjectUtils.isEmpty(userContext.get())
                || ObjectUtils.isArray(userContext.get().userCode);
        if (isNull) {
            throw new IcBoLuoException("No userCode in Context");
        }
        return userContext.get().userCode;
    }

    public static void set(String userCode) {
        AdminUser adminUser = new AdminUser(userCode);
        userContext.set(adminUser);
    }

    public static void remove() {
        userContext.remove();
    }
}
