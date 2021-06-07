package com.icboluo.mapper;

import com.icboluo.entity.FundInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (FundInfo)表数据库访问层
 *
 * @author icboluo
 * @since 2021-06-08 00:57:17
 */
public interface FundInfoMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    FundInfo queryById(String id);

    /**
     * 查询所有
     *
     * @return 对象列表
     */
    List<FundInfo> queryAll();

    /**
     * 通过实体作为筛选条件查询
     *
     * @param fundInfo 实例对象
     * @return 对象列表
     */
    List<FundInfo> queryAllByData(FundInfo fundInfo);

    /**
     * 新增数据
     *
     * @param fundInfo 实例对象
     * @return 影响行数
     */
    int insert(FundInfo fundInfo);

    /**
     * 新增数据
     *
     * @param fundInfo 实例对象
     * @return 影响行数
     */
    int insertSelective(FundInfo fundInfo);

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

    /**
     * 修改数据
     *
     * @param fundInfo 实例对象
     * @return 影响行数
     */
    int update(FundInfo fundInfo);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(String id);

}

