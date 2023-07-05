package com.icboluo.util;

import com.baomidou.mybatisplus.core.MybatisParameterHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * and name like concat ('%',#{name},'%') escape '/'
 *
 * @author icboluo
 * @since 2023-07-05 19:13
 */
@Component
@Intercepts({
        @Signature(type = ProcessHandle.class, method = "setParameters", args = {PreparedStatement.class})
})
@Slf4j
public class MyBatisInterceptor implements Interceptor {
    private static final Map<Class<?>, List<Field>> CLASS_CACHE = new HashMap<>();


    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        try {
            MybatisParameterHandler target = (MybatisParameterHandler) invocation.getTarget();
            Field sqlCommandTypeField = target.getClass().getDeclaredField("sqlCommandType");
            sqlCommandTypeField.setAccessible(true);
            SqlCommandType sqlCommandType = (SqlCommandType) sqlCommandTypeField.get(target);
            if (sqlCommandType == SqlCommandType.SELECT) {
                Object parameterObject = target.getParameterObject();
                List<Field> fields = paramHandler(parameterObject.getClass());
                for (Field field : fields) {
                    field.setAccessible(true);
                    String oldVal = (String) field.get(parameterObject);
                    String newVal = likeSelectHandler(oldVal);
                    field.set(parameterObject, newVal);
                }
            }
        } catch (Exception e) {
            log.error("mybatis error ", e);
            return invocation.proceed();
        }
        return invocation.proceed();
    }

    private List<Field> paramHandler(Class<?> clazz) {
        CLASS_CACHE.computeIfAbsent(clazz, key -> Arrays.stream(clazz.getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(LikeMatch.class))
                .collect(Collectors.toList())
        );
        return CLASS_CACHE.get(clazz);
    }

    private String likeSelectHandler(String str) {
        if (StringUtils.hasText(str)) {
            str = str.replace("%", "/%");
            str = str.replace("_", "/_");
        }
        return str;
    }
}
