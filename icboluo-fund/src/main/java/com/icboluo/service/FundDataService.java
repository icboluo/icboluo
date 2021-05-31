package com.icboluo.service;

import com.icboluo.entity.FundData;
import com.icboluo.object.FundDataVO;
import com.icboluo.object.query.FundDataQuery;

import java.util.List;

/**
 * (FundData)表服务接口
 *
 * @author icboluo
 * @since 2021-05-28 00:12:37
 */
public interface FundDataService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    FundData queryById(Long id);

    /**
     * 新增数据
     *
     * @param fundData 实例对象
     * @return 实例对象
     */
    FundData insert(FundData fundData);

    /**
     * 修改数据
     *
     * @param fundData 实例对象
     * @return 实例对象
     */
    FundData update(FundData fundData);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

    List<FundData> selectByQuery(FundDataQuery query);

    FundDataVO cal(String fundId);
}
