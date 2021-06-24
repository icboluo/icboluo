package com.icboluo.service;

import com.icboluo.entity.FundAsyncRecord;

/**
 * (FundAsyncRecord)表服务接口
 *
 * @author icboluo
 * @since 2021-06-08 00:09:34
 */
public interface FundAsyncRecordService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    FundAsyncRecord queryById(String id);

    /**
     * 新增数据
     *
     * @param fundAsyncRecord 实例对象
     * @return 实例对象
     */
    FundAsyncRecord insert(FundAsyncRecord fundAsyncRecord);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(String id);

}
