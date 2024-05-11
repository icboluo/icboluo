package com.icboluo.service;

import com.icboluo.common.Constant;
import com.icboluo.entity.*;
import com.icboluo.entity.inter.FiledResultInter;
import com.icboluo.entity.inter.NoteCommonInter;
import com.icboluo.entity.inter.NoteViewInter;
import com.icboluo.enumerate.ReEnum;
import com.icboluo.mapper.*;
import com.icboluo.object.client.TimeNoteCO;
import com.icboluo.object.client.TimeUpdateCO;
import com.icboluo.object.query.TimeNoteQuery;
import com.icboluo.object.view.FiledResultVO;
import com.icboluo.object.view.NoteVO;
import com.icboluo.util.BeanUtil;
import com.icboluo.util.DateUtil;
import com.icboluo.util.IcBoLuoException;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;
import java.util.stream.Stream;

/**
 * @author lp
 */
@Service
@Slf4j
public class NoteServiceImpl implements NoteService {
    @Resource
    private TimeNoteMapper timeNoteMapper;
    @Resource
    private WeekTimeMapper weekTimeMapper;
    @Resource
    private MonthTimeMapper monthTimeMapper;
    @Resource
    private YearTimeMapper yearTimeMapper;
    @Resource
    private FinishMapper finishMapper;
    @Resource
    private OnlyReadMapper onlyReadMapper;
    @Resource
    private TimeNoteService timeNoteService;
    @Resource
    private WeekTimeService weekTimeService;
    @Resource
    private MonthTimeService monthTimeService;
    @Resource
    private YearTimeService yearTimeService;

    @Override
    public List<NoteVO> selectList(TimeNoteQuery query) {
        List<NoteTimeNote> timeList = timeNoteMapper.selectAll(query);
        List<NoteWeekTime> weekList = weekTimeMapper.selectAll(query);
        List<NoteMonthTime> monthList = monthTimeMapper.selectAll();
        List<NoteYearTime> yearList = yearTimeMapper.selectAll();
        List<NoteVO> noteList1 = deleteUnqualifiedObj(timeList, Constant.TIME_NOTE_INTERVAL);
        List<NoteVO> noteList2 = deleteUnqualifiedObj(weekList, Constant.WEEK_TIME_INTERVAL);
        List<NoteVO> noteList3 = deleteUnqualifiedObj(monthList, Constant.MONTH_TIME_INTERVAL);
        List<NoteVO> noteList4 = deleteUnqualifiedObj(yearList, Constant.YEAR_TIME_INTERVAL);

        return Stream.of(noteList1, noteList2, noteList3, noteList4)
                .flatMap(Collection::stream)
                .sorted(Comparator.comparing(NoteVO::getShouldFinishTime))
                .toList();
    }

    /**
     * 删除不合格(时间上不满足的情况)对象
     *
     * @param list time whole list
     * @return qualified list
     */
    private <T extends NoteViewInter & NoteCommonInter> List<NoteVO> deleteUnqualifiedObj(List<T> list, long timeInterval) {
        return list.stream()
                .filter(o -> {
                    LocalDateTime gmtModified = o.getGmtModified();
                    long planTime = gmtModified.toEpochSecond(ZoneOffset.ofHours(8)) + timeInterval;
                    long time = System.currentTimeMillis();
                    return time > planTime;
                })
                .map(o -> {
                    NoteVO vo = o.toView();
                    LocalDateTime gmtModified = o.getGmtModified();
                    long planTime = DateUtil.toLong(gmtModified) + timeInterval;
                    vo.setShouldFinishTime(DateUtil.toDateTime(planTime));
                    return vo;
                }).toList();
    }


    /**
     * 更新未完成次数
     *
     * @param dd 包含type id
     */
    @Override
    public void updateNotFinishTime(TimeUpdateCO dd) {
        String type = dd.getType();
        Integer id = dd.getId();
        if (Constant.TIME_TYPE.equals(type)) {
            NoteTimeNote noteTimeNote = timeNoteService.getUpdateObj(id, Constant.NOT_FINISH);
            timeNoteMapper.updateByPrimaryKeySelective(noteTimeNote);
            return;
        }
        if (Constant.WEEK_TYPE.equals(type)) {
            NoteWeekTime noteWeekTime = weekTimeMapper.selectByPrimaryKey(id);
            if (0 == noteWeekTime.getFinishTime()) {
                timeNoteMapper.insertSelective(noteWeekTime.toTime());
                weekTimeMapper.deleteByPrimaryKey(id);
            } else {
                noteWeekTime = weekTimeService.getUpdateObj(id, Constant.NOT_FINISH);
                weekTimeMapper.updateByPrimaryKeySelective(noteWeekTime);
            }
            return;
        }
        if (Constant.MONTH_TYPE.equals(type)) {
            NoteMonthTime monthTime = monthTimeMapper.selectByPrimaryKey(id);
            if (0 == monthTime.getFinishTime()) {
                weekTimeMapper.insertSelective(monthTime.toWeek());
                monthTimeMapper.deleteByPrimaryKey(id);
            } else {
                NoteMonthTime noteMonthTime = monthTimeService.getUpdateObj(id, Constant.NOT_FINISH);
                monthTimeMapper.updateByPrimaryKeySelective(noteMonthTime);
            }
            return;
        }
        if (Constant.YEAR_TYPE.equals(type)) {
            NoteYearTime yearTime = yearTimeMapper.selectByPrimaryKey(id);
            if (0 == yearTime.getFinishTime()) {
                monthTimeMapper.insertSelective(yearTime.toMonth());
                yearTimeMapper.deleteByPrimaryKey(id);
            } else {
                NoteYearTime noteYearTime = yearTimeService.getUpdateObj(id, Constant.NOT_FINISH);
                yearTimeMapper.updateByPrimaryKeySelective(noteYearTime);
            }
        }
    }

    @Override
    public void update(TimeNoteCO client, int id, String type) {
        if (Constant.TIME_TYPE.equals(type)) {
            NoteTimeNote noteTimeNote = timeNoteMapper.selectByPrimaryKey(id);
            BeanUtil.notEmptyThenSet(client, noteTimeNote, TimeNoteCO::getBelongToScope, NoteTimeNote::setBelongToScope);
            BeanUtil.notEmptyThenSet(client, noteTimeNote, TimeNoteCO::getProblem, NoteTimeNote::setProblem);
            BeanUtil.notEmptyThenSet(client, noteTimeNote, TimeNoteCO::getResult, NoteTimeNote::setResult);
            noteTimeNote.setGmtModified(LocalDateTime.now());
            timeNoteMapper.updateByPrimaryKeySelective(noteTimeNote);
        } else if (Constant.WEEK_TYPE.equals(type)) {
            NoteWeekTime noteWeekTime = weekTimeMapper.selectByPrimaryKey(id);
            BeanUtil.notEmptyThenSet(client, noteWeekTime, TimeNoteCO::getBelongToScope, NoteWeekTime::setBelongToScope);
            BeanUtil.notEmptyThenSet(client, noteWeekTime, TimeNoteCO::getProblem, NoteWeekTime::setProblem);
            BeanUtil.notEmptyThenSet(client, noteWeekTime, TimeNoteCO::getResult, NoteWeekTime::setResult);
            noteWeekTime.setGmtModified(LocalDateTime.now());
            weekTimeMapper.updateByPrimaryKeySelective(noteWeekTime);
        } else if (Constant.MONTH_TYPE.equals(type)) {
            NoteMonthTime noteMonthTime = monthTimeMapper.selectByPrimaryKey(id);
            BeanUtil.notEmptyThenSet(client, noteMonthTime, TimeNoteCO::getBelongToScope, NoteMonthTime::setBelongToScope);
            BeanUtil.notEmptyThenSet(client, noteMonthTime, TimeNoteCO::getProblem, NoteMonthTime::setProblem);
            BeanUtil.notEmptyThenSet(client, noteMonthTime, TimeNoteCO::getResult, NoteMonthTime::setResult);
            noteMonthTime.setGmtModified(LocalDateTime.now());
            monthTimeMapper.updateByPrimaryKeySelective(noteMonthTime);
        }
    }

    /**
     * 查询问题以及结果
     *
     * @param file contain problem result and belongToScope
     * @return result list
     */
    @Override
    public List<FiledResultVO> select(String file) {
        List<NoteTimeNote> timeNoteList = timeNoteMapper.select(file);
        List<NoteWeekTime> weekTimeList = weekTimeMapper.select(file);
        List<NoteMonthTime> monthTimeList = monthTimeMapper.select(file);
        List<NoteYearTime> yearTimeList = yearTimeMapper.select(file);
        List<NoteFinish> finishList = finishMapper.select(file);

        return Stream.of(timeNoteList, weekTimeList, monthTimeList, yearTimeList, finishList)
                .flatMap(Collection::stream)
                .map(FiledResultInter::toFiledResultView)
                .toList();
    }

    @Override
    public void toOnlyRead(TimeUpdateCO client) {
        Integer id = client.getId();
        NoteTimeNote noteTimeNote = timeNoteMapper.selectByPrimaryKey(id);
        timeNoteMapper.deleteByPrimaryKey(id);
        NoteOnlyRead noteOnlyRead = BeanUtil.copyProperties(noteTimeNote, NoteOnlyRead.class);
        noteOnlyRead.setGmtModified(null);
        onlyReadMapper.insertSelective(noteOnlyRead);
    }

    @SuppressWarnings("all")
    @Override
    public void onlyUpdateTime(TimeUpdateCO client) {
        String type = client.getType();
        Integer id = client.getId();
        NoteCommonInter noteCommonInter = simpleFactory(type);
        noteCommonInter.setId(id);
        noteCommonInter.setGmtModified(LocalDateTime.now());
        // 这个default我不想加的，代码规范过不去
        switch (type) {
            case (Constant.TIME_TYPE) -> timeNoteMapper.updateByPrimaryKeySelective((NoteTimeNote) noteCommonInter);
            case (Constant.WEEK_TYPE) -> weekTimeMapper.updateByPrimaryKeySelective((NoteWeekTime) noteCommonInter);
            case (Constant.MONTH_TYPE) -> monthTimeMapper.updateByPrimaryKeySelective((NoteMonthTime) noteCommonInter);
            case (Constant.YEAR_TYPE) -> yearTimeMapper.updateByPrimaryKeySelective((NoteYearTime) noteCommonInter);
            default -> throw new IcBoLuoException(ReEnum.PARAM_ERROR);
        }
    }

    private NoteCommonInter simpleFactory(String type) {
        return switch (type) {
            case (Constant.TIME_TYPE) -> new NoteTimeNote();
            case (Constant.WEEK_TYPE) -> new NoteWeekTime();
            case (Constant.MONTH_TYPE) -> new NoteMonthTime();
            case (Constant.YEAR_TYPE) -> new NoteYearTime();
            default -> throw new IcBoLuoException(ReEnum.PARAM_ERROR);
        };
    }

    @Override
    public void updateFinishTime(TimeUpdateCO dd) {
        String type = dd.getType();
        Integer id = dd.getId();
        if (Constant.TIME_TYPE.equals(type)) {
            NoteTimeNote timeNote = timeNoteMapper.selectByPrimaryKey(id);
            if (Constant.TIME_FINISH_TIME - 1 <= timeNote.getFinishTime()) {
                weekTimeMapper.insertSelective(timeNote.toWeek());
                timeNoteMapper.deleteByPrimaryKey(id);
            } else {
                NoteTimeNote noteTimeNote = new NoteTimeNote();
                noteTimeNote.setId(id);
                noteTimeNote.setFinishTime(timeNote.getFinishTime() + 1);
                timeNoteMapper.updateByPrimaryKeySelective(noteTimeNote);
            }
        } else if (Constant.WEEK_TYPE.equals(type)) {
            NoteWeekTime weekTime = weekTimeMapper.selectByPrimaryKey(id);
            if (Constant.WEEK_FINISH_TIME - 1 <= weekTime.getFinishTime()) {
                monthTimeMapper.insertSelective(weekTime.toMonth());
                weekTimeMapper.deleteByPrimaryKey(id);
            } else {
                NoteWeekTime noteWeekTime = new NoteWeekTime();
                noteWeekTime.setId(id);
                noteWeekTime.setFinishTime(weekTime.getFinishTime() + 1);
                weekTimeMapper.updateByPrimaryKeySelective(noteWeekTime);
            }
        } else if (Constant.MONTH_TYPE.equals(type)) {
            NoteMonthTime monthTime = monthTimeMapper.selectByPrimaryKey(id);
            if (Constant.MONTH_FINISH_TIME - 1 <= monthTime.getFinishTime()) {
                yearTimeMapper.insertSelective(monthTime.toYear());
                monthTimeMapper.deleteByPrimaryKey(id);
            } else {
                NoteMonthTime noteMonthTime = new NoteMonthTime();
                noteMonthTime.setId(id);
                noteMonthTime.setFinishTime(monthTime.getFinishTime() + 1);
                monthTimeMapper.updateByPrimaryKeySelective(noteMonthTime);
            }
        } else if (Constant.YEAR_TYPE.equals(type)) {
            NoteYearTime yearTime = yearTimeMapper.selectByPrimaryKey(id);
            if (Constant.YEAR_FINISH_TIME - 1 <= yearTime.getFinishTime()) {
                finishMapper.insertSelective(yearTime.toFinish());
                yearTimeMapper.deleteByPrimaryKey(id);
            } else {
                NoteYearTime noteYearTime = new NoteYearTime();
                noteYearTime.setId(id);
                yearTime.setFinishTime(yearTime.getFinishTime() + 1);
                yearTimeMapper.updateByPrimaryKeySelective(yearTime);
            }
        }
        log.debug(type + " upLocalDateTime success");
    }

    @Override
    public Map<String, Integer> selectAmount() {
        List<NoteTimeNote> timeList = timeNoteMapper.selectAll(new TimeNoteQuery());
        List<NoteWeekTime> weekList = weekTimeMapper.selectAll(new TimeNoteQuery());
        List<NoteMonthTime> monthList = monthTimeMapper.selectAll();
        List<NoteVO> noteList1 = deleteUnqualifiedObj(timeList, Constant.TIME_NOTE_INTERVAL);
        List<NoteVO> noteList2 = deleteUnqualifiedObj(weekList, Constant.WEEK_TIME_INTERVAL);
        List<NoteVO> noteList3 = deleteUnqualifiedObj(monthList, Constant.MONTH_TIME_INTERVAL);

        int totalTimeAmount = noteList1.size() + noteList2.size() + noteList3.size();
        Map<String, Integer> map = new HashMap<>(4);
        map.put("timeNoteAmount", noteList1.size());
        map.put("weekTimeAmount", noteList2.size());
        map.put("monthTimeAmount", noteList3.size());
        map.put("totalTimeAmount", totalTimeAmount);
        return map;
    }
}
