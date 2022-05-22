package com.icboluo.mapper;

import com.icboluo.entity.note.TimeNoteDO;
import com.icboluo.object.query.TimeNoteQuery;

import java.util.List;

/**
 * TimeNoteMapping继承基类
 * @author lp
 */
public interface TimeNoteMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(TimeNoteDO record);

    int insertSelective(TimeNoteDO record);

    TimeNoteDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TimeNoteDO record);

    int updateByPrimaryKey(TimeNoteDO record);

    List<TimeNoteDO> selectAll(TimeNoteQuery query);

    List<TimeNoteDO> select(String file);
}
