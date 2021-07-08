package com.icboluo.mapper;

import com.icboluo.MyBaseMapper;
import com.icboluo.entity.FundInfo;

import java.util.List;

/**
 * (FundInfo)表数据库访问层
 *
 * @author icboluo
 * @since 2021-06-24 23:18:17
 */
public interface FundInfoMapper extends MyBaseMapper<FundInfo> {

    /**
     * 通过实体作为筛选条件查询
     *
     * @param fundInfo 实例对象
     * @return 对象列表
     */
    List<FundInfo> queryAllByData(FundInfo fundInfo);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<FundInfo> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(List<FundInfo> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<FundInfo> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(List<FundInfo> entities);
}

