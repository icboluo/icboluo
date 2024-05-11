package com.icboluo.mapper;

import com.icboluo.entity.NoteMonthTime;

import java.util.List;


/**
 * MonthTimeMapping继承基类
 * @author lp
 */
public interface MonthTimeMapper{

    int deleteByPrimaryKey(Integer id);

    int insert(NoteMonthTime record);

    int insertSelective(NoteMonthTime record);

    NoteMonthTime selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(NoteMonthTime record);

    int updateByPrimaryKey(NoteMonthTime record);

    List<NoteMonthTime> selectAll();

    List<NoteMonthTime> select(String file);
}
