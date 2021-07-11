package com.icboluo.service;

import com.github.pagehelper.PageInfo;
import com.icboluo.entity.FundData;
import com.icboluo.object.query.FundDataQuery;
import com.icboluo.object.vo.FundDataCalVO;
import com.icboluo.object.vo.FundDataRecentVO;
import com.icboluo.object.vo.FundDataVO;

import java.time.LocalDate;

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
}
