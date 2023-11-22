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

    PageInfo<FundAttentionVO> init(FundAttentionQuery query);

    void delete(String id);
}
