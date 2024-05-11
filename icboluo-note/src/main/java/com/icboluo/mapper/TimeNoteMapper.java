package com.icboluo.mapper;

import com.icboluo.entity.NoteTimeNote;
import com.icboluo.object.query.TimeNoteQuery;

import java.util.List;

/**
 * TimeNoteMapping继承基类
 * @author lp
 */
public interface TimeNoteMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(NoteTimeNote record);

    int insertSelective(NoteTimeNote record);

    NoteTimeNote selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(NoteTimeNote record);

    int updateByPrimaryKey(NoteTimeNote record);

    List<NoteTimeNote> selectAll(TimeNoteQuery query);

    List<NoteTimeNote> select(String file);
}
