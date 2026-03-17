package com.icboluo.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.icboluo.entity.ProjectConfig;

/**
 * @author icboluo
 * @since 2026-03-17 21:52
 */
public interface ProjectConfigMapper extends BaseMapper<ProjectConfig> {
    default ProjectConfig selectByPidAndKey(Integer pid, String name) {
        LambdaQueryWrapper<ProjectConfig> qw = new LambdaQueryWrapper<>();
        qw.eq(ProjectConfig::getPid, pid);
        qw.eq(ProjectConfig::getKey, name);
        return selectOne(qw);
    }

    default void deleteByPidAndKey(Integer pid, String name) {
        LambdaQueryWrapper<ProjectConfig> qw = new LambdaQueryWrapper<>();
        qw.eq(ProjectConfig::getPid, pid);
        qw.eq(ProjectConfig::getKey, name);
        delete(qw);
    }
}
