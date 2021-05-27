package com.icboluo.mapper;

import com.icboluo.entity.FundAttention;

import java.util.List;

/**
 * (FundAttention)表数据库访问层
 *
 * @author icboluo
 * @since 2021-05-27 23:04:59
 */
public interface FundAttentionMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    FundAttention queryById(String id);

    /**
     * 查询所有
     *
     * @return 对象列表
     */
    List<FundAttention> queryAll();

    /**
     * 通过实体作为筛选条件查询
     *
     * @param fundAttention 实例对象
     * @return 对象列表
     */
    List<FundAttention> queryAllByData(FundAttention fundAttention);

    /**
     * 新增数据
     *
     * @param fundAttention 实例对象
     * @return 影响行数
     */
    int insert(FundAttention fundAttention);

    /**
     * 新增数据
     *
     * @param fundAttention 实例对象
     * @return 影响行数
     */
    int insertSelective(FundAttention fundAttention);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<FundAttention> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(List<FundAttention> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<FundAttention> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(List<FundAttention> entities);

    /**
     * 修改数据
     *
     * @param fundAttention 实例对象
     * @return 影响行数
     */
    int update(FundAttention fundAttention);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(String id);

}
