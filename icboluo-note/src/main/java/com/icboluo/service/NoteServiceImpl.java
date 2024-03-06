package com.icboluo.service;

import com.icboluo.common.Constant;
import com.icboluo.entity.note.*;
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
        List<TimeNoteDO> timeList = timeNoteMapper.selectAll(query);
        List<WeekTimeDO> weekList = weekTimeMapper.selectAll(query);
        List<MonthTimeDO> monthList = monthTimeMapper.selectAll();
        List<YearTimeDO> yearList = yearTimeMapper.selectAll();
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
            TimeNoteDO timeNoteDO = timeNoteService.getUpdateObj(id, Constant.NOT_FINISH);
            timeNoteMapper.updateByPrimaryKeySelective(timeNoteDO);
            return;
        }
        if (Constant.WEEK_TYPE.equals(type)) {
            WeekTimeDO weekTimeDO = weekTimeMapper.selectByPrimaryKey(id);
            if (0 == weekTimeDO.getFinishTime()) {
                timeNoteMapper.insertSelective(weekTimeDO.toTime());
                weekTimeMapper.deleteByPrimaryKey(id);
            } else {
                weekTimeDO = weekTimeService.getUpdateObj(id, Constant.NOT_FINISH);
                weekTimeMapper.updateByPrimaryKeySelective(weekTimeDO);
            }
            return;
        }
        if (Constant.MONTH_TYPE.equals(type)) {
            MonthTimeDO monthTime = monthTimeMapper.selectByPrimaryKey(id);
            if (0 == monthTime.getFinishTime()) {
                weekTimeMapper.insertSelective(monthTime.toWeek());
                monthTimeMapper.deleteByPrimaryKey(id);
            } else {
                MonthTimeDO monthTimeDO = monthTimeService.getUpdateObj(id, Constant.NOT_FINISH);
                monthTimeMapper.updateByPrimaryKeySelective(monthTimeDO);
            }
            return;
        }
        if (Constant.YEAR_TYPE.equals(type)) {
            YearTimeDO yearTime = yearTimeMapper.selectByPrimaryKey(id);
            if (0 == yearTime.getFinishTime()) {
                monthTimeMapper.insertSelective(yearTime.toMonth());
                yearTimeMapper.deleteByPrimaryKey(id);
            } else {
                YearTimeDO yearTimeDO = yearTimeService.getUpdateObj(id, Constant.NOT_FINISH);
                yearTimeMapper.updateByPrimaryKeySelective(yearTimeDO);
            }
        }
    }

    @Override
    public void update(TimeNoteCO client, int id, String type) {
        if (Constant.TIME_TYPE.equals(type)) {
            TimeNoteDO timeNoteDO = timeNoteMapper.selectByPrimaryKey(id);
            BeanUtil.notEmptyThenSet(client, timeNoteDO, TimeNoteCO::getBelongToScope, TimeNoteDO::setBelongToScope);
            BeanUtil.notEmptyThenSet(client, timeNoteDO, TimeNoteCO::getProblem, TimeNoteDO::setProblem);
            BeanUtil.notEmptyThenSet(client, timeNoteDO, TimeNoteCO::getResult, TimeNoteDO::setResult);
            timeNoteDO.setGmtModified(LocalDateTime.now());
            timeNoteMapper.updateByPrimaryKeySelective(timeNoteDO);
        } else if (Constant.WEEK_TYPE.equals(type)) {
            WeekTimeDO weekTimeDO = weekTimeMapper.selectByPrimaryKey(id);
            BeanUtil.notEmptyThenSet(client, weekTimeDO, TimeNoteCO::getBelongToScope, WeekTimeDO::setBelongToScope);
            BeanUtil.notEmptyThenSet(client, weekTimeDO, TimeNoteCO::getProblem, WeekTimeDO::setProblem);
            BeanUtil.notEmptyThenSet(client, weekTimeDO, TimeNoteCO::getResult, WeekTimeDO::setResult);
            weekTimeDO.setGmtModified(LocalDateTime.now());
            weekTimeMapper.updateByPrimaryKeySelective(weekTimeDO);
        } else if (Constant.MONTH_TYPE.equals(type)) {
            MonthTimeDO monthTimeDO = monthTimeMapper.selectByPrimaryKey(id);
            BeanUtil.notEmptyThenSet(client, monthTimeDO, TimeNoteCO::getBelongToScope, MonthTimeDO::setBelongToScope);
            BeanUtil.notEmptyThenSet(client, monthTimeDO, TimeNoteCO::getProblem, MonthTimeDO::setProblem);
            BeanUtil.notEmptyThenSet(client, monthTimeDO, TimeNoteCO::getResult, MonthTimeDO::setResult);
            monthTimeDO.setGmtModified(LocalDateTime.now());
            monthTimeMapper.updateByPrimaryKeySelective(monthTimeDO);
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
        List<TimeNoteDO> timeNoteList = timeNoteMapper.select(file);
        List<WeekTimeDO> weekTimeList = weekTimeMapper.select(file);
        List<MonthTimeDO> monthTimeList = monthTimeMapper.select(file);
        List<YearTimeDO> yearTimeList = yearTimeMapper.select(file);
        List<FinishDO> finishList = finishMapper.select(file);

        return Stream.of(timeNoteList, weekTimeList, monthTimeList, yearTimeList, finishList)
                .flatMap(Collection::stream)
                .map(FiledResultInter::toFiledResultView)
                .toList();
    }

    @Override
    public void toOnlyRead(TimeUpdateCO client) {
        Integer id = client.getId();
        TimeNoteDO timeNoteDO = timeNoteMapper.selectByPrimaryKey(id);
        timeNoteMapper.deleteByPrimaryKey(id);
        OnlyReadDO onlyReadDO = BeanUtil.copyProperties(timeNoteDO, OnlyReadDO.class);
        onlyReadDO.setGmtModified(null);
        onlyReadMapper.insertSelective(onlyReadDO);
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
            case (Constant.TIME_TYPE) -> timeNoteMapper.updateByPrimaryKeySelective((TimeNoteDO) noteCommonInter);
            case (Constant.WEEK_TYPE) -> weekTimeMapper.updateByPrimaryKeySelective((WeekTimeDO) noteCommonInter);
            case (Constant.MONTH_TYPE) -> monthTimeMapper.updateByPrimaryKeySelective((MonthTimeDO) noteCommonInter);
            case (Constant.YEAR_TYPE) -> yearTimeMapper.updateByPrimaryKeySelective((YearTimeDO) noteCommonInter);
            default -> throw new IcBoLuoException(ReEnum.PARAM_ERROR);
        }
    }

    private NoteCommonInter simpleFactory(String type) {
        return switch (type) {
            case (Constant.TIME_TYPE) -> new TimeNoteDO();
            case (Constant.WEEK_TYPE) -> new WeekTimeDO();
            case (Constant.MONTH_TYPE) -> new MonthTimeDO();
            case (Constant.YEAR_TYPE) -> new YearTimeDO();
            default -> throw new IcBoLuoException(ReEnum.PARAM_ERROR);
        };
    }

    @Override
    public void updateFinishTime(TimeUpdateCO dd) {
        String type = dd.getType();
        Integer id = dd.getId();
        if (Constant.TIME_TYPE.equals(type)) {
            TimeNoteDO timeNote = timeNoteMapper.selectByPrimaryKey(id);
            if (Constant.TIME_FINISH_TIME - 1 <= timeNote.getFinishTime()) {
                weekTimeMapper.insertSelective(timeNote.toWeek());
                timeNoteMapper.deleteByPrimaryKey(id);
            } else {
                TimeNoteDO timeNoteDO = new TimeNoteDO();
                timeNoteDO.setId(id);
                timeNoteDO.setFinishTime(timeNote.getFinishTime() + 1);
                timeNoteMapper.updateByPrimaryKeySelective(timeNoteDO);
            }
        } else if (Constant.WEEK_TYPE.equals(type)) {
            WeekTimeDO weekTime = weekTimeMapper.selectByPrimaryKey(id);
            if (Constant.WEEK_FINISH_TIME - 1 <= weekTime.getFinishTime()) {
                monthTimeMapper.insertSelective(weekTime.toMonth());
                weekTimeMapper.deleteByPrimaryKey(id);
            } else {
                WeekTimeDO weekTimeDO = new WeekTimeDO();
                weekTimeDO.setId(id);
                weekTimeDO.setFinishTime(weekTime.getFinishTime() + 1);
                weekTimeMapper.updateByPrimaryKeySelective(weekTimeDO);
            }
        } else if (Constant.MONTH_TYPE.equals(type)) {
            MonthTimeDO monthTime = monthTimeMapper.selectByPrimaryKey(id);
            if (Constant.MONTH_FINISH_TIME - 1 <= monthTime.getFinishTime()) {
                yearTimeMapper.insertSelective(monthTime.toYear());
                monthTimeMapper.deleteByPrimaryKey(id);
            } else {
                MonthTimeDO monthTimeDO = new MonthTimeDO();
                monthTimeDO.setId(id);
                monthTimeDO.setFinishTime(monthTime.getFinishTime() + 1);
                monthTimeMapper.updateByPrimaryKeySelective(monthTimeDO);
            }
        } else if (Constant.YEAR_TYPE.equals(type)) {
            YearTimeDO yearTime = yearTimeMapper.selectByPrimaryKey(id);
            if (Constant.YEAR_FINISH_TIME - 1 <= yearTime.getFinishTime()) {
                finishMapper.insertSelective(yearTime.toFinish());
                yearTimeMapper.deleteByPrimaryKey(id);
            } else {
                YearTimeDO yearTimeDO = new YearTimeDO();
                yearTimeDO.setId(id);
                yearTime.setFinishTime(yearTime.getFinishTime() + 1);
                yearTimeMapper.updateByPrimaryKeySelective(yearTime);
            }
        }
        log.debug(type + " upLocalDateTime success");
    }

    @Override
    public Map<String, Integer> selectAmount() {
        List<TimeNoteDO> timeList = timeNoteMapper.selectAll(new TimeNoteQuery());
        List<WeekTimeDO> weekList = weekTimeMapper.selectAll(new TimeNoteQuery());
        List<MonthTimeDO> monthList = monthTimeMapper.selectAll();
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
