package com.icboluo.annotation;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

/**
 * @author icboluo
 * @since 2024-03-28 0:29
 */
public enum IdNameEnum {
    /**
     * 空
     */
    NULL,

    /**
     * 国际化
     */
    I18N,
    ;

    private Executor asyncExecutor;


    /**
     * 预处理
     *
     * @return 预处理结果
     */
    public CompletableFuture<Map<?, String>> preHandler() {
        if (this == I18N) {
            return CompletableFuture.supplyAsync(() -> new HashMap<>(), asyncExecutor);
        }
        return CompletableFuture.supplyAsync(HashMap::new);
    }

    /**
     * 批量查询
     *
     * @param list 查询code
     * @return id-name映射
     */
    public Map<?, String> selectMap(List<Objects> list) {
        if (this == I18N) {
            return new HashMap<>();
        }
        return new HashMap<>();
    }

    public String findNameByCode(Map<?, String> idNameMap, Object code) {
        if (this == I18N) {
            return idNameMap.get(code);
        }
        return null;
    }

    /**
     * 本类唯一的作用就是间接将bean注入到枚举中
     */
    @Component
    static class EnumAutowired {
        @Autowired
        private Executor asyncExecutor;

        @PostConstruct
        public void postConstruct() {
            for (IdNameEnum item : EnumSet.allOf(IdNameEnum.class)) {
                item.asyncExecutor = asyncExecutor;
            }
        }
    }
}
