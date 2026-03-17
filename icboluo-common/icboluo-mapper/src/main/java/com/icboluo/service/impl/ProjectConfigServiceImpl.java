package com.icboluo.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.icboluo.common.ProjectEnum;
import com.icboluo.entity.ProjectConfig;
import com.icboluo.mapper.ProjectConfigMapper;
import com.icboluo.service.ProjectConfigService;
import com.icboluo.util.I18nException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author icboluo
 * @since 2026-03-17 21:49
 */
@Service
@AllArgsConstructor
public class ProjectConfigServiceImpl implements ProjectConfigService {
    private final ProjectConfigMapper projectConfigMapper;

    private final ObjectMapper om;

    @Override
    public <T> void insertOrUpdate(Integer pid, ProjectEnum key, T value) {
        ProjectConfig db = new ProjectConfig();
        db.setPid(pid);
        db.setKey(key.name());
        try {
            db.setValue(om.writeValueAsString(value));
        } catch (JsonProcessingException e) {
            throw new I18nException("JsonProcessingException", e);
        }
        projectConfigMapper.deleteByPidAndKey(pid, key.name());
        projectConfigMapper.insert(db);
    }

    /**
     * 根据项目id和key查询数据
     *
     * @param pid          项目id
     * @param key          枚举key
     * @param valueTypeRef 例如：new TypeReference<List<String>>() {}
     * @param <T>          配置类型
     * @return 配置value
     */
    @Override
    public <T> T selectByPidAndKey(Integer pid, ProjectEnum key, TypeReference<T> valueTypeRef) {
        ProjectConfig projectConfig = projectConfigMapper.selectByPidAndKey(pid, key.name());
        if (projectConfig == null) {
            return null;
        }
        try {
            return om.readValue(projectConfig.getValue(), valueTypeRef);
        } catch (JsonProcessingException e) {
            throw new I18nException("JsonProcessingException", e);
        }
    }
}
