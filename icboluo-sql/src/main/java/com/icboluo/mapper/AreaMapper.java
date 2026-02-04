package com.icboluo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.icboluo.entity.Area;

import java.util.List;

/**
 * (Area)表数据库访问层
 *
 * @author icboluo
 * @since 2023-07-07 06:01:17
 */
public interface AreaMapper extends BaseMapper<Area> {

    /**
     * 查询指定数据
     *
     * @param area 查询条件
     * @return 对象列表
     */
    List<Area> queryByAllField(Area area);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Area> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(List<Area> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Area> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(List<Area> entities);
}

