package com.icboluo.mapper;

import com.icboluo.entity.NoteYearTime;

import java.util.List;

/**
 * YearTimeMapping继承基类
 *
 * @author lp
 */
public interface YearTimeMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(NoteYearTime record);

    int insertSelective(NoteYearTime record);

    NoteYearTime selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(NoteYearTime record);

    int updateByPrimaryKey(NoteYearTime record);

    List<NoteYearTime> select(String file);

    List<NoteYearTime> selectAll();
}
