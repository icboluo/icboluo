package com.icboluo.mapper;

import com.icboluo.entity.note.FinishDO;

import java.util.List;

/**
 * FinishMapping继承基类
 *
 * @author lp
 */
public interface FinishMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(FinishDO record);

    int insertSelective(FinishDO record);

    FinishDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FinishDO record);

    int updateByPrimaryKey(FinishDO record);

    List<FinishDO> select(String file);
}
