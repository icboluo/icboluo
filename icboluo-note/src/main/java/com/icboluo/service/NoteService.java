package com.icboluo.service;

import com.icboluo.object.client.TimeNoteCO;
import com.icboluo.object.client.TimeUpdateCO;
import com.icboluo.object.query.TimeNoteQuery;
import com.icboluo.object.view.FiledResultVO;
import com.icboluo.object.view.NoteVO;

import java.util.List;
import java.util.Map;

/**
 * @author icboluo
 * @since 2021-10-25 0:27
 */
public interface NoteService {

    List<NoteVO> selectList(TimeNoteQuery query);

    /**
     * 更新未完成次数
     *
     * @param dd 包含type id
     */
    void updateNotFinishTime(TimeUpdateCO dd);

    /**
     * upLocalDateTime belongToScope problem and result
     *
     * @param client upLocalDateTime obj
     * @param id     id
     * @param type   type
     */
    void update(TimeNoteCO client, int id, String type);

    /**
     * 查询问题以及结果
     *
     * @param file contain problem result and belongToScope
     * @return result list
     */
    List<FiledResultVO> select(String file);

    void toOnlyRead(TimeUpdateCO client);

    /**
     * 仅仅更新更新时间
     *
     * @param client 包含type id
     */
    void onlyUpdateTime(TimeUpdateCO client);

    /**
     * 更新完成次数
     *
     * @param client 包含type id
     */
    void updateFinishTime(TimeUpdateCO client);

    Map<String, Integer> selectAmount();
}
