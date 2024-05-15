package com.icboluo.mapper;

import com.icboluo.common.MyBaseMapper;
import com.icboluo.entity.FundConfigWeight;

import java.util.List;

/**
 * 基金--配置--权重(FundConfigWeight)表数据库访问层
 *
 * @author icboluo
 * @since 2024-05-15 18:41:03
 */
public interface FundConfigWeightMapper extends MyBaseMapper<FundConfigWeight> {
    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<FundConfigWeight> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(List<FundConfigWeight> entities);

    /**
     * 批量更新数据（MyBatis原生foreach方法）,因为是逐条更新，所以返回值永远是1，没有必要返回
     * 该方法实现为多个update sql拼接，如果调用处增加事物注解，则标签拥有事物，如果不加，则没有
     *
     * @param entities List<FundConfigWeight> 实例对象列表
     */
    void updateBatchSelective(List<FundConfigWeight> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<FundConfigWeight> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(List<FundConfigWeight> entities);
}

