package com.icboluo.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.icboluo.common.MyBaseMapper;
import com.icboluo.entity.FundData;
import com.icboluo.object.query.FundDataChooseQuery;
import com.icboluo.object.query.FundDataQuery;
import com.icboluo.object.vo.FundDataVO;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;
import java.util.List;

/**
 * (FundData)表数据库访问层
 *
 * @author icboluo
 * @since 2021-05-28 00:12:36
 */
public interface FundDataMapper extends MyBaseMapper<FundData> {
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

    void deleteByFundIdDate(@Param("fundId") String fundId, @Param("date") LocalDate date);

    default List<FundData> selectByFundIds(List<String> fundIds) {
        LambdaQueryWrapper<FundData> qw = new LambdaQueryWrapper<>();
        qw.in(FundData::getFundId, fundIds);
        return selectList(qw);
    }
}

