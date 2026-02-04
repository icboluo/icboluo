package com.icboluo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.icboluo.entity.FundAttention;
import com.icboluo.object.query.FundAttentionQuery;
import com.icboluo.object.vo.FundAttentionVO;

import java.util.List;

/**
 * (FundAttention)表数据库访问层
 *
 * @author icboluo
 * @since 2021-05-27 23:04:59
 */
public interface FundAttentionMapper extends BaseMapper<FundAttention> {
    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<FundAttention> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(List<FundAttention> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<FundAttention> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(List<FundAttention> entities);

    List<FundAttentionVO> selectByQuery(FundAttentionQuery query);

}

