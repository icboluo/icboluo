package com.icboluo.mapper;

import com.icboluo.entity.NoteFinish;

import java.util.List;

/**
 * FinishMapping继承基类
 *
 * @author lp
 */
public interface FinishMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(NoteFinish record);

    int insertSelective(NoteFinish record);

    NoteFinish selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(NoteFinish record);

    int updateByPrimaryKey(NoteFinish record);

    List<NoteFinish> select(String file);
}
