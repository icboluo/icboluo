package com.icboluo.service;

import com.icboluo.common.Constant;
import com.icboluo.entity.NoteTimeNote;
import com.icboluo.mapper.TimeNoteMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * @author icboluo
 */
@Service
public class TimeNoteServiceImpl implements TimeNoteService {
    @Resource
    private TimeNoteMapper timeNoteMapper;

    @Override
    public NoteTimeNote getUpdateObj(Integer id, Integer isFinish) {
        NoteTimeNote timeNote = timeNoteMapper.selectByPrimaryKey(id);
        NoteTimeNote noteTimeNote = new NoteTimeNote();
        if (isFinish == Constant.NOT_FINISH) {
            noteTimeNote.setFinishTime(Math.max(timeNote.getFinishTime() - 1, 0));
        }
        noteTimeNote.setId(id);
        noteTimeNote.setGmtModified(LocalDateTime.now());
        return noteTimeNote;
    }
}
