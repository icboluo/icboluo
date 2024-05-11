package com.icboluo.service;

import com.icboluo.entity.NoteTimeNote;

/**
 * @author icboluo
 * @since 2021-10-25 0:26
 */
public interface TimeNoteService {

    NoteTimeNote getUpdateObj(Integer id, Integer isFinish);
}
