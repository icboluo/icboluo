package com.icboluo.mapper;

import com.icboluo.entity.FundAsyncRecord;

import java.util.List;

/**
 * (FundAsyncRecord)表数据库访问层
 *
 * @author icboluo
 * @since 2021-06-08 00:09:33
 */
public interface FundAsyncRecordMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    FundAsyncRecord queryById(String id);

    /**
     * 查询所有
     *
     * @return 对象列表
     */
    List<FundAsyncRecord> queryAll();

    /**
     * 通过实体作为筛选条件查询
     *
     * @param fundAsyncRecord 实例对象
     * @return 对象列表
     */
    List<FundAsyncRecord> queryAllByData(FundAsyncRecord fundAsyncRecord);

    /**
     * 新增数据
     *
     * @param fundAsyncRecord 实例对象
     * @return 影响行数
     */
    int insert(FundAsyncRecord fundAsyncRecord);

    /**
     * 新增数据
     *
     * @param fundAsyncRecord 实例对象
     * @return 影响行数
     */
    int insertSelective(FundAsyncRecord fundAsyncRecord);

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

    /**
     * 修改数据
     *
     * @param fundAsyncRecord 实例对象
     * @return 影响行数
     */
    int update(FundAsyncRecord fundAsyncRecord);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(String id);

}

