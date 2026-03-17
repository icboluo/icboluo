package com.icboluo.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.icboluo.common.ProjectEnum;

/**
 * @author icboluo
 * @since 2026-03-17 21:49
 */
public interface ProjectConfigService {
    <T> void insertOrUpdate(Integer pid, ProjectEnum key, T value);

    /**
     * 根据项目id和key查询数据
     *
     * @param pid          项目id
     * @param key          枚举key
     * @param valueTypeRef 例如：new TypeReference<List<String>>() {}
     * @param <T>          配置类型
     * @return 配置value
     */
    <T> T selectByPidAndKey(Integer pid, ProjectEnum key, TypeReference<T> valueTypeRef);
}



