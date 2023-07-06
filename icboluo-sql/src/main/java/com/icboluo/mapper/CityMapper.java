package com.icboluo.mapper;

import com.icboluo.common.MyBaseMapper;
import com.icboluo.entity.City;

import java.util.List;

/**
 * (City)表数据库访问层
 *
 * @author icboluo
 * @since 2023-07-07 06:01:40
 */
public interface CityMapper extends MyBaseMapper<City> {

    /**
     * 查询指定数据
     *
     * @param city 查询条件
     * @return 对象列表
     */
    List<City> queryByAllField(City city);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<City> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(List<City> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<City> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(List<City> entities);
}

