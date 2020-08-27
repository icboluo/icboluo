package com.icboluo.dao;

import com.icboluo.object.dataobject.YearTimeDO;

import java.util.List;

/**
 * YearTimeMapping继承基类
 *
 * @author lp
 */
public interface YearTimeMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(YearTimeDO record);

    int insertSelective(YearTimeDO record);

    YearTimeDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(YearTimeDO record);

    int updateByPrimaryKey(YearTimeDO record);

    List<YearTimeDO> select(String file);

    List<YearTimeDO> selectAll();
}
