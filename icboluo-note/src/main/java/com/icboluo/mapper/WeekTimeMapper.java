package com.icboluo.mapper;

import com.icboluo.entity.NoteWeekTime;
import com.icboluo.object.query.TimeNoteQuery;

import java.util.List;

/**
 * WeekTimeMapping继承基类
 * @author lp
 */
public interface WeekTimeMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(NoteWeekTime record);

    int insertSelective(NoteWeekTime record);

    NoteWeekTime selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(NoteWeekTime record);

    int updateByPrimaryKey(NoteWeekTime record);

    List<NoteWeekTime> selectAll(TimeNoteQuery query);

    List<NoteWeekTime> select(String file);
}
