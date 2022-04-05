package com.icboluo.service;

import com.icboluo.object.dataobject.TimeNoteDO;

/**
 * @author icboluo
 * @since 2021-10-25 0:26
 */
public interface TimeNoteService {

    TimeNoteDO getUpdateObj(Integer id, Integer isFinish);
}
