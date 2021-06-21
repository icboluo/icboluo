package com.icboluo.service;

import com.icboluo.entity.FundAttention;
import com.icboluo.object.query.FundAttentionQuery;
import com.icboluo.object.vo.FundAttentionVO;

import java.util.List;

/**
 * (FundAttention)表服务接口
 *
 * @author icboluo
 * @since 2021-05-27 23:05:00
 */
public interface FundAttentionService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    FundAttention queryById(String id);

    /**
     * 新增数据
     *
     * @param fundAttention 实例对象
     * @return 实例对象
     */
    FundAttention insert(FundAttention fundAttention);

    /**
     * 修改数据
     *
     * @param fundAttention 实例对象
     * @return 实例对象
     */
    FundAttention update(FundAttention fundAttention);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(String id);

    List<FundAttentionVO> init(FundAttentionQuery query);

    void delete(String id);
}
