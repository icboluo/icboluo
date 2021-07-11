package com.icboluo.mapper;

import com.icboluo.MyBaseMapper;
import com.icboluo.entity.FundData;
import com.icboluo.object.query.FundDataChooseQuery;
import com.icboluo.object.query.FundDataQuery;
import com.icboluo.object.vo.FundDataVO;

import java.util.List;

/**
 * (FundData)表数据库访问层
 *
 * @author icboluo
 * @since 2021-05-28 00:12:36
 */
public interface FundDataMapper extends MyBaseMapper<FundData> {

    /**
     * 通过实体作为筛选条件查询
     *
     * @param fundData 实例对象
     * @return 对象列表
     */
    List<FundData> queryAllByData(FundData fundData);

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

    List<FundDataVO> selectByQuery(FundDataQuery query);

    List<FundData> selectByFundId(String id);

    void deleteByFundId(String fundId);

    List<FundDataVO> selectChooseDate(FundDataChooseQuery curQuery);

    List<FundDataVO> selectChooseAll(String fundId);
}

