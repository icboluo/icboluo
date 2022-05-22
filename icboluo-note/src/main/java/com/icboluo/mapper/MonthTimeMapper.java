package com.icboluo.mapper;

import com.icboluo.entity.note.MonthTimeDO;

import java.util.List;


/**
 * MonthTimeMapping继承基类
 * @author lp
 */
public interface MonthTimeMapper{

    int deleteByPrimaryKey(Integer id);

    int insert(MonthTimeDO record);

    int insertSelective(MonthTimeDO record);

    MonthTimeDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MonthTimeDO record);

    int updateByPrimaryKey(MonthTimeDO record);

    List<MonthTimeDO> selectAll();

    List<MonthTimeDO> select(String file);
}
