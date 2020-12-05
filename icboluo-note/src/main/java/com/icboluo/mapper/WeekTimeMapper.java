package com.icboluo.mapper;

import com.icboluo.object.dataobject.WeekTimeDO;
import com.icboluo.object.query.TimeNoteQuery;

import java.util.List;

/**
 * WeekTimeMapping继承基类
 * @author lp
 */
public interface WeekTimeMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(WeekTimeDO record);

    int insertSelective(WeekTimeDO record);

    WeekTimeDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WeekTimeDO record);

    int updateByPrimaryKey(WeekTimeDO record);

    List<WeekTimeDO> selectAll(TimeNoteQuery query);

    List<WeekTimeDO> select(String file);
}
