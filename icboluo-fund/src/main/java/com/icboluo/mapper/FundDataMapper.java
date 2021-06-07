package com.icboluo.mapper;

import com.icboluo.entity.FundData;
import com.icboluo.object.query.FundDataQuery;
import com.icboluo.object.vo.FundDataVO;

import java.util.List;

/**
 * (FundData)表数据库访问层
 *
 * @author icboluo
 * @since 2021-05-28 00:12:36
 */
public interface FundDataMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    FundData queryById(Long id);

    /**
     * 查询所有
     *
     * @return 对象列表
     */
    List<FundData> queryAll();

    /**
     * 通过实体作为筛选条件查询
     *
     * @param fundData 实例对象
     * @return 对象列表
     */
    List<FundData> queryAllByData(FundData fundData);

    /**
     * 新增数据
     *
     * @param fundData 实例对象
     * @return 影响行数
     */
    int insert(FundData fundData);

    /**
     * 新增数据
     *
     * @param fundData 实例对象
     * @return 影响行数
     */
    int insertSelective(FundData fundData);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<FundData> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(List<FundData> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<FundData> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(List<FundData> entities);

    /**
     * 修改数据
     *
     * @param fundData 实例对象
     * @return 影响行数
     */
    int update(FundData fundData);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

    List<FundDataVO> selectByQuery(FundDataQuery query);

    List<FundData> selectByFundId(String id);
}

