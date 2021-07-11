package com.icboluo.service;

import com.icboluo.object.vo.FundInfoVO;

/**
 * (FundInfo)表服务接口
 *
 * @author icboluo
 * @since 2021-06-08 00:57:18
 */
public interface FundInfoService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    FundInfoVO fundInfoInit(String id);
}
