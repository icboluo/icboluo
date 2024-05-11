package com.icboluo.service;

import com.github.pagehelper.PageInfo;
import com.icboluo.object.query.FundAttentionQuery;
import com.icboluo.object.vo.FundAttentionVO;

/**
 * (FundAttention)表服务接口
 *
 * @author icboluo
 * @since 2021-05-27 23:05:00
 */
public interface FundAttentionService {

    /**
     * 基金列表初始化
     *
     * @param query 查询条件
     * @return 主表值
     */
    PageInfo<FundAttentionVO> init(FundAttentionQuery query);

    /**
     * 删除基金
     *
     * @param id 基金id
     */
    void delete(String id);
}
