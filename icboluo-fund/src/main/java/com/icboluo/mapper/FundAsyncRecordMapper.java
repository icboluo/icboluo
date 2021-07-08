package com.icboluo.mapper;

import com.icboluo.MyBaseMapper;
import com.icboluo.entity.FundAsyncRecord;

import java.util.List;

/**
 * (FundAsyncRecord)表数据库访问层
 *
 * @author icboluo
 * @since 2021-06-08 00:09:33
 */
public interface FundAsyncRecordMapper extends MyBaseMapper<FundAsyncRecord> {

    /**
     * 通过实体作为筛选条件查询
     *
     * @param fundAsyncRecord 实例对象
     * @return 对象列表
     */
    List<FundAsyncRecord> queryAllByData(FundAsyncRecord fundAsyncRecord);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<FundAsyncRecord> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(List<FundAsyncRecord> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<FundAsyncRecord> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(List<FundAsyncRecord> entities);
}

