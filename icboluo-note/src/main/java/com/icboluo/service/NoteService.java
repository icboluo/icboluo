package com.icboluo.service;

import com.icboluo.object.clientobject.DDD;
import com.icboluo.object.clientobject.TimeNoteCO;
import com.icboluo.object.query.TimeNoteQuery;
import com.icboluo.object.viewobject.FiledResultVO;
import com.icboluo.object.viewobject.NoteAllVO;
import com.icboluo.object.viewobject.NoteVO;

import java.util.List;
import java.util.Map;

/**
 * @author icboluo
 * @date 2021-10-25 0:27
 */
public interface NoteService {

    /**
     * 查询第一个应该解决的问题
     *
     * @param query condition of this select
     * @return vo obj
     */
    NoteAllVO selectOne(TimeNoteQuery query);

    List<NoteVO> selectList(TimeNoteQuery query);

    Map<String, String> selectIdAndType(TimeNoteQuery query);

    /**
     * 更新未完成次数
     *
     * @param dd 包含type id
     */
    void updateNotFinishTime(DDD dd);

    /**
     * upLocalDateTime belongToScope problem and result
     *
     * @param timeNoteCO upLocalDateTime obj
     * @param map        k is id type
     */
    void update(TimeNoteCO timeNoteCO, Map<String, String> map);

    /**
     * 查询问题以及结果
     *
     * @param file contain problem result and belongToScope
     * @return result list
     */
    List<FiledResultVO> select(String file);

    void toOnlyRead(DDD dd);

    /**
     * 仅仅更新更新时间
     *
     * @param dd 包含type id
     */
    void onlyUpdateTime(DDD dd);

    /**
     * 更新完成次数
     *
     * @param dd 包含type id
     */
    void updateFinishTime(DDD dd);
}
