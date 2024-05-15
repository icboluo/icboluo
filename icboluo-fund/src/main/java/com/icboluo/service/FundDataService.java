package com.icboluo.service;

import com.github.pagehelper.PageInfo;
import com.icboluo.entity.FundData;
import com.icboluo.object.query.FundDataQuery;
import com.icboluo.object.query.FundWeightQuery;
import com.icboluo.object.vo.FundDataCalVO;
import com.icboluo.object.vo.FundDataRecentVO;
import com.icboluo.object.vo.FundDataVO;
import com.icboluo.object.vo.FundMetricVo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * (FundData)表服务接口
 *
 * @author icboluo
 * @since 2021-05-28 00:12:37
 */
public interface FundDataService {

    /**
     * 新增数据
     *
     * @param fundData 实例对象
     * @return 实例对象
     */
    FundData insert(FundData fundData);

    PageInfo<FundDataVO> selectByQuery(FundDataQuery query);

    FundDataCalVO cal(String fundId, LocalDate startDate);

    FundDataRecentVO findRecentData(String fundId, LocalDate myChooseDate);

    /**
     * 增加今天的数据
     *
     * @param fundId 基金id
     * @param rate   日增至率
     */
    void addToday(String fundId, BigDecimal rate);

    /**
     * 最近10天增长率
     *
     * @param fundList 有序基金列表（按日期倒序
     * @return 最近10天平均增长率
     */
    BigDecimal last10DaysAvg(List<FundData> fundList);

    /**
     * 年增长率（第二年的时候加上了第一年
     *
     * @param fundList 有序基金列表（按日期倒序
     * @return 年平均增长率
     */
    Map<Integer, BigDecimal> yearAvg(List<FundData> fundList);

    void delete(String id);

    List<FundMetricVo> selectWeight(FundWeightQuery query);
}
