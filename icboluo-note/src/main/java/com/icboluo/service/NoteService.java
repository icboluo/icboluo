package com.icboluo.service;

import com.icboluo.common.Constant;
import com.icboluo.convertor.MonthTimeConvertor;
import com.icboluo.convertor.TimeNoteConvertor;
import com.icboluo.convertor.WeekTimeConvertor;
import com.icboluo.convertor.YearTimeConvertor;
import com.icboluo.mapper.*;
import com.icboluo.object.clientobject.TimeNoteCO;
import com.icboluo.object.dataobject.*;
import com.icboluo.object.query.TimeNoteQuery;
import com.icboluo.object.viewobject.FiledResultVO;
import com.icboluo.object.viewobject.NoteVO;
import com.icboluo.util.BeanHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author lp
 */
@Service
@Slf4j
public class NoteService {
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
    private TimeNoteConvertor timeNoteConvertor;
    @Resource
    private WeekTimeConvertor weekTimeConvertor;
    @Resource
    private MonthTimeConvertor monthTimeConvertor;
    @Resource
    private YearTimeConvertor yearTimeConvertor;
    @Resource
    private TimeNoteService timeNoteService;
    @Resource
    private WeekTimeService weekTimeService;
    @Resource
    private MonthTimeService monthTimeService;
    @Resource
    private YearTimeService yearTimeService;

    /**
     * 查询第一个应该解决的问题
     *
     * @param query condition of this select
     * @return vo obj
     */
    public NoteVO selectOne(TimeNoteQuery query) {
        List<TimeNoteDO> timeList = timeNoteMapper.selectAll(query);
        List<WeekTimeDO> weekList = weekTimeMapper.selectAll(query);
        List<MonthTimeDO> monthList = monthTimeMapper.selectAll();
        List<YearTimeDO> yearList = yearTimeMapper.selectAll();
        List<NoteVO> noteList1 = deleteUnqualifiedObj(timeList, TimeNoteDO::getGmtModified, Constant.TIME_NOTE_INTERVAL, timeNoteConvertor::toView);
        List<NoteVO> noteList2 = deleteUnqualifiedObj(weekList, WeekTimeDO::getGmtModified, Constant.WEEK_TIME_INTERVAL, weekTimeConvertor::toView);
        List<NoteVO> noteList3 = deleteUnqualifiedObj(monthList, MonthTimeDO::getGmtModified, Constant.MONTH_TIME_INTERVAL, monthTimeConvertor::toView);
        List<NoteVO> noteList4 = deleteUnqualifiedObj(yearList, YearTimeDO::getGmtModified, Constant.YEAR_TIME_INTERVAL, yearTimeConvertor::toView);

        NoteVO noteVO = Stream.of(noteList1, noteList2, noteList3, noteList4)
                .flatMap(Collection::stream)
                .min(Comparator.comparing(NoteVO::getGmtModified))
                .orElse(new NoteVO());
        noteVO.setTimeNoteAmount(noteList1.size());
        noteVO.setWeekTimeAmount(noteList2.size());
        noteVO.setMonthTimeAmount(noteList3.size());
        return noteVO;
    }

    public Map<String, String> selectIdAndType(TimeNoteQuery query) {
        NoteVO noteVO = selectOne(query);
        Map<String, String> map = new HashMap<>(2);
        map.put("id", noteVO.getId() + "");
        map.put("type", noteVO.getType());
        return map;
    }

    /**
     * todo
     * 删除不合格(时间上不满足的情况)对象
     *
     * @param list time whole list
     * @return qualified list
     */
    private <T> List<NoteVO> deleteUnqualifiedObj(List<T> list, Function<T, LocalDateTime> getGmtModified, long timeInterval, Function<T, NoteVO> convertor) {
        return list.stream()
                .filter(o -> {
                    LocalDateTime gmtModified = getGmtModified.apply(o);
                    long planTime = gmtModified.toEpochSecond(ZoneOffset.ofHours(8)) + timeInterval;
                    long time = System.currentTimeMillis();
                    return time > planTime;
                })
                .map(o -> {
                    NoteVO vo = convertor.apply(o);
                    LocalDateTime gmtModified = getGmtModified.apply(o);
                    long planTime = gmtModified.toEpochSecond(ZoneOffset.ofHours(8)) + timeInterval;
                    vo.setShouldFinishTime(planTime);
                    return vo;
                }).collect(Collectors.toList());
    }

    /**
     * 更新完成次数
     *
     * @param map k is type and id
     */
    public void updateFinishTime(Map<String, String> map) {
        String type = map.get("type");
        Integer id = Integer.valueOf(map.get("id"));
        if (Constant.TIME_TYPE.equals(type)) {
            TimeNoteDO timeNote = timeNoteMapper.selectByPrimaryKey(id);
            if (Constant.TIME_FINISH_TIME - 1 <= timeNote.getFinishTime()) {
                weekTimeMapper.insertSelective(timeNoteConvertor.toWeek(timeNote));
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
                monthTimeMapper.insertSelective(weekTimeConvertor.toMonth(weekTime));
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
                yearTimeMapper.insertSelective(monthTimeConvertor.toYear(monthTime));
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
                finishMapper.insertSelective(yearTimeConvertor.toFinish(yearTime));
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

    /**
     * 更新未完成次数
     *
     * @param map k is type id
     */
    public void updateNotFinishTime(Map<String, String> map) {
        String type = map.get("type");
        Integer id = Integer.valueOf(map.get("id"));
        if (Constant.TIME_TYPE.equals(type)) {
            TimeNoteDO timeNoteDO = timeNoteService.getUpdateObj(id, Constant.NOT_FINISH);
            timeNoteMapper.updateByPrimaryKeySelective(timeNoteDO);
            return;
        }
        if (Constant.WEEK_TYPE.equals(type)) {
            WeekTimeDO weekTimeDO = weekTimeMapper.selectByPrimaryKey(id);
            if (0 == weekTimeDO.getFinishTime()) {
                timeNoteMapper.insertSelective(weekTimeConvertor.toTime(weekTimeDO));
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
                weekTimeMapper.insertSelective(monthTimeConvertor.toWeek(monthTime));
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
                monthTimeMapper.insertSelective(yearTimeConvertor.toMonth(yearTime));
                yearTimeMapper.deleteByPrimaryKey(id);
            } else {
                YearTimeDO yearTimeDO = yearTimeService.getUpdateObj(id, Constant.NOT_FINISH);
                yearTimeMapper.updateByPrimaryKeySelective(yearTimeDO);
            }
        }
    }


    /**
     * 仅仅更新更新时间
     *
     * @param map k is type id
     */
    public void onlyUpdateTime(Map<String, String> map) {
        String type = map.get("type");
        Integer id = Integer.valueOf(map.get("id"));
        if (Constant.TIME_TYPE.equals(type)) {
            TimeNoteDO timeNoteDO = new TimeNoteDO();
            timeNoteDO.setId(id);
            timeNoteDO.setGmtModified(LocalDateTime.now());
            timeNoteMapper.updateByPrimaryKeySelective(timeNoteDO);
        } else if (Constant.WEEK_TYPE.equals(type)) {
            WeekTimeDO weekTimeDO = new WeekTimeDO();
            weekTimeDO.setId(id);
            weekTimeDO.setGmtModified(LocalDateTime.now());
            weekTimeMapper.updateByPrimaryKeySelective(weekTimeDO);
        } else if (Constant.MONTH_TYPE.equals(type)) {
            MonthTimeDO monthTimeDO = new MonthTimeDO();
            monthTimeDO.setId(id);
            monthTimeDO.setGmtModified(LocalDateTime.now());
            monthTimeMapper.updateByPrimaryKeySelective(monthTimeDO);
        } else if (Constant.YEAR_TYPE.equals(type)) {
            YearTimeDO yearTimeDO = new YearTimeDO();
            yearTimeDO.setId(id);
            yearTimeDO.setGmtModified(LocalDateTime.now());
            yearTimeMapper.updateByPrimaryKeySelective(yearTimeDO);
        }
    }

    /**
     * upLocalDateTime belongToScope problem and result
     *
     * @param timeNoteCO upLocalDateTime obj
     * @param map        k is id type
     */
    public void update(TimeNoteCO timeNoteCO, Map<String, String> map) {
        String type = map.get("type");
        Integer id = Integer.valueOf(map.get("id"));
        if (Constant.TIME_TYPE.equals(type)) {
            TimeNoteDO timeNoteDO = timeNoteMapper.selectByPrimaryKey(id);
            BeanHelper.notEmptyThenSet(timeNoteCO, timeNoteDO, TimeNoteCO::getBelongToScope, TimeNoteDO::setBelongToScope);
            BeanHelper.notEmptyThenSet(timeNoteCO, timeNoteDO, TimeNoteCO::getProblem, TimeNoteDO::setProblem);
            BeanHelper.notEmptyThenSet(timeNoteCO, timeNoteDO, TimeNoteCO::getResult, TimeNoteDO::setResult);
            timeNoteDO.setGmtModified(LocalDateTime.now());
            timeNoteMapper.updateByPrimaryKeySelective(timeNoteDO);
        } else if (Constant.WEEK_TYPE.equals(type)) {
            WeekTimeDO weekTimeDO = weekTimeMapper.selectByPrimaryKey(id);
            BeanHelper.notEmptyThenSet(timeNoteCO, weekTimeDO, TimeNoteCO::getBelongToScope, WeekTimeDO::setBelongToScope);
            BeanHelper.notEmptyThenSet(timeNoteCO, weekTimeDO, TimeNoteCO::getProblem, WeekTimeDO::setProblem);
            BeanHelper.notEmptyThenSet(timeNoteCO, weekTimeDO, TimeNoteCO::getResult, WeekTimeDO::setResult);
            weekTimeDO.setGmtModified(LocalDateTime.now());
            weekTimeMapper.updateByPrimaryKeySelective(weekTimeDO);
        } else if (Constant.MONTH_TYPE.equals(type)) {
            MonthTimeDO monthTimeDO = monthTimeMapper.selectByPrimaryKey(id);
            BeanHelper.notEmptyThenSet(timeNoteCO, monthTimeDO, TimeNoteCO::getBelongToScope, MonthTimeDO::setBelongToScope);
            BeanHelper.notEmptyThenSet(timeNoteCO, monthTimeDO, TimeNoteCO::getProblem, MonthTimeDO::setProblem);
            BeanHelper.notEmptyThenSet(timeNoteCO, monthTimeDO, TimeNoteCO::getResult, MonthTimeDO::setResult);
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
    public List<FiledResultVO> select(String file) {
        List<FiledResultVO> filedResultVOS = new ArrayList<>();
        List<TimeNoteDO> timeNoteDOS = timeNoteMapper.select(file);
        List<WeekTimeDO> weekTimeDOS = weekTimeMapper.select(file);
        List<MonthTimeDO> monthTimeDOS = monthTimeMapper.select(file);
        List<YearTimeDO> yearTimeDOS = yearTimeMapper.select(file);
        List<FinishDO> finishDOS = finishMapper.select(file);
        List<FiledResultVO> filedResultVOS1 = BeanHelper.copyWithCollection(timeNoteDOS, FiledResultVO.class);
        filedResultVOS1.forEach(f -> f.setType(Constant.TIME_TYPE));
        List<FiledResultVO> filedResultVOS2 = BeanHelper.copyWithCollection(weekTimeDOS, FiledResultVO.class);
        filedResultVOS2.forEach(f -> f.setType(Constant.WEEK_TYPE));
        List<FiledResultVO> filedResultVOS3 = BeanHelper.copyWithCollection(monthTimeDOS, FiledResultVO.class);
        filedResultVOS3.forEach(f -> f.setType(Constant.MONTH_TYPE));
        List<FiledResultVO> filedResultVOS4 = BeanHelper.copyWithCollection(yearTimeDOS, FiledResultVO.class);
        filedResultVOS4.forEach(f -> f.setType(Constant.YEAR_TYPE));
        List<FiledResultVO> filedResultVOS5 = BeanHelper.copyWithCollection(finishDOS, FiledResultVO.class);
        filedResultVOS5.forEach(f -> f.setType(Constant.FINISH_TYPE));

        filedResultVOS.addAll(filedResultVOS1);
        filedResultVOS.addAll(filedResultVOS2);
        filedResultVOS.addAll(filedResultVOS3);
        filedResultVOS.addAll(filedResultVOS4);
        filedResultVOS.addAll(filedResultVOS5);
        return BeanHelper.copyWithCollection(filedResultVOS, FiledResultVO.class);
    }

    public void toOnlyRead() {
        TimeNoteQuery query = new TimeNoteQuery();
        NoteVO noteVO = selectOne(query);
        TimeNoteDO timeNoteDO = timeNoteMapper.selectByPrimaryKey(noteVO.getId());
        timeNoteMapper.deleteByPrimaryKey(noteVO.getId());
        OnlyReadDO onlyReadDO = BeanHelper.copyProperties(timeNoteDO, OnlyReadDO.class);
        onlyReadDO.setGmtModified(null);
        onlyReadMapper.insertSelective(onlyReadDO);
    }
}
