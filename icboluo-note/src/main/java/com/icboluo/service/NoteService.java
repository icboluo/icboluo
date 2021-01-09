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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;

/**
 * @author lp
 */
@Service
@Transactional
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
        List<TimeNoteDO> timeNoteDOS = timeNoteMapper.selectAll(query);
        List<WeekTimeDO> weekTimeDOS = weekTimeMapper.selectAll(query);
        List<MonthTimeDO> monthTimeDOS = monthTimeMapper.selectAll();
        List<YearTimeDO> yearTimeDOS = yearTimeMapper.selectAll();
        List<TimeNoteDO> timeNoteDOList = deleteUnqualifiedObj1(timeNoteDOS);
        List<WeekTimeDO> weekTimeDOList = deleteUnqualifiedObj2(weekTimeDOS);
        List<MonthTimeDO> monthTimeDOList = deleteUnqualifiedObj3(monthTimeDOS);
        List<YearTimeDO> yearTimeDOList = deleteUnqualifiedObj4(yearTimeDOS);

        TimeNoteDO timeNoteDO;
        WeekTimeDO weekTimeDO;
        MonthTimeDO monthTimeDO;
        YearTimeDO yearTimeDO;
        if (timeNoteDOList.isEmpty()) {
            timeNoteDO = new TimeNoteDO();
            timeNoteDO.setGmtModified(LocalDateTime.now());
        } else {
            timeNoteDO = timeNoteDOList.get(0);
        }
        if (weekTimeDOList.isEmpty()) {
            weekTimeDO = new WeekTimeDO();
            weekTimeDO.setGmtModified(LocalDateTime.now());
        } else {
            weekTimeDO = weekTimeDOList.get(0);
        }
        if (monthTimeDOList.isEmpty()) {
            monthTimeDO = new MonthTimeDO();
            monthTimeDO.setGmtModified(LocalDateTime.now());
        } else {
            monthTimeDO = monthTimeDOList.get(0);
        }
        if (yearTimeDOList.isEmpty()) {
            yearTimeDO = new YearTimeDO();
            yearTimeDO.setGmtModified(LocalDateTime.now());
        } else {
            yearTimeDO = yearTimeDOList.get(0);
        }
        NoteVO noteVO = getMinTime(timeNoteDO, weekTimeDO, monthTimeDO, yearTimeDO);
        noteVO.setTimeNoteAmount(timeNoteDOList.size());
        noteVO.setWeekTimeAmount(weekTimeDOList.size());
        noteVO.setMonthTimeAmount(monthTimeDOList.size());
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
     * 删除不合格对象
     *
     * @param list year whole
     * @return qualified obj
     */
    private List<YearTimeDO> deleteUnqualifiedObj4(List<YearTimeDO> list) {
        int i = 0;
        for (YearTimeDO yearTimeDO : list) {
            LocalDateTime gmtModified = yearTimeDO.getGmtModified();
            long shouldTime = gmtModified.toEpochSecond(ZoneOffset.ofHours(8)) + Constant.YEAR_TIME_INTERVAL;
            long time = System.currentTimeMillis();
            if (time < shouldTime) {
                break;
            }
            i++;
        }
        return list.subList(0, i);
    }

    /**
     * 比较对象中时间值最小的
     *
     * @param timeNoteDO  time obj
     * @param weekTimeDO  week obj
     * @param monthTimeDO month obj
     * @param yearTimeDO  year obj
     * @return vo obj
     */
    private NoteVO getMinTime(TimeNoteDO timeNoteDO, WeekTimeDO weekTimeDO, MonthTimeDO monthTimeDO, YearTimeDO yearTimeDO) {
        long time1 = timeNoteDO.getGmtModified().toEpochSecond(ZoneOffset.ofHours(8)) + Constant.TIME_NOTE_INTERVAL;
        long time2 = weekTimeDO.getGmtModified().toEpochSecond(ZoneOffset.ofHours(8)) + Constant.WEEK_TIME_INTERVAL;
        long time3 = monthTimeDO.getGmtModified().toEpochSecond(ZoneOffset.ofHours(8)) + Constant.MONTH_TIME_INTERVAL;
        long time4 = yearTimeDO.getGmtModified().toEpochSecond(ZoneOffset.ofHours(8)) + Constant.YEAR_TIME_INTERVAL;

        List<Long> list = new ArrayList<>();
        list.add(time1);
        list.add(time2);
        list.add(time3);
        list.add(time4);
        long min = Long.MAX_VALUE;
        int type = 0;
        for (int i = 0; i < 4; i++) {
            if (min > list.get(i)) {
                min = list.get(i);
                type = i + 1;
            }
        }
        NoteVO noteVO = null;
        if (1 == type) {
            noteVO = timeNoteConvertor.toView(timeNoteDO);
        } else if (2 == type) {
            noteVO = weekTimeConvertor.toView(weekTimeDO);
        } else if (3 == type) {
            noteVO = monthTimeConvertor.toView(monthTimeDO);
        } else if (4 == type) {
            noteVO = yearTimeConvertor.toView(yearTimeDO);
        }
        return noteVO;
    }

    /**
     * 删除不合格的对象
     *
     * @param list month whole
     * @return qualified list
     */
    private List<MonthTimeDO> deleteUnqualifiedObj3(List<MonthTimeDO> list) {
        int i = 0;
        for (MonthTimeDO monthTimeDO : list) {
            LocalDateTime gmtModified = monthTimeDO.getGmtModified();
            long planTime = gmtModified.toEpochSecond(ZoneOffset.ofHours(8)) + Constant.MONTH_TIME_INTERVAL;
            long time = System.currentTimeMillis();
            if (time < planTime) {
                break;
            }
            i++;
        }
        return list.subList(0, i);
    }

    /**
     * 删除不合格(时间上不满足的情况)对象
     *
     * @param list time whole list
     * @return qualified list
     */
    private List<TimeNoteDO> deleteUnqualifiedObj1(List<TimeNoteDO> list) {
        int i = 0;
        for (TimeNoteDO timeNoteDO : list) {
            LocalDateTime gmtModified = timeNoteDO.getGmtModified();
            long planTime = gmtModified.toEpochSecond(ZoneOffset.ofHours(8)) + Constant.TIME_NOTE_INTERVAL;
            long time = System.currentTimeMillis();
            if (time < planTime) {
                break;
            }
            i++;
        }
        return list.subList(0, i);
    }

    /**
     * 删除不合格对象
     *
     * @param list week whole list
     * @return qualified list
     */
    private List<WeekTimeDO> deleteUnqualifiedObj2(List<WeekTimeDO> list) {
        int i = 0;
        for (WeekTimeDO weekTimeDO : list) {
            LocalDateTime gmtModified = weekTimeDO.getGmtModified();
            long planTime = gmtModified.toEpochSecond(ZoneOffset.ofHours(8)) + Constant.WEEK_TIME_INTERVAL;
            long time = System.currentTimeMillis();
            if (time < planTime) {
                break;
            }
            i++;
        }
        return list.subList(0, i);
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
            if (!StringUtils.isEmpty(timeNoteCO.getBelongToScope())) {
                timeNoteDO.setBelongToScope(timeNoteCO.getBelongToScope());
            }
            if (!StringUtils.isEmpty(timeNoteCO.getProblem())) {
                timeNoteDO.setProblem(timeNoteCO.getProblem());
            }
            if (!StringUtils.isEmpty(timeNoteCO.getResult())) {
                timeNoteDO.setResult(timeNoteCO.getResult());
            }
            timeNoteDO.setGmtModified(LocalDateTime.now());
            timeNoteMapper.updateByPrimaryKeySelective(timeNoteDO);
        } else if (Constant.WEEK_TYPE.equals(type)) {
            WeekTimeDO weekTimeDO = weekTimeMapper.selectByPrimaryKey(id);
            if (!StringUtils.isEmpty(timeNoteCO.getBelongToScope())) {
                weekTimeDO.setBelongToScope(timeNoteCO.getBelongToScope());
            }
            if (!StringUtils.isEmpty(timeNoteCO.getProblem())) {
                weekTimeDO.setProblem(timeNoteCO.getProblem());
            }
            if (!StringUtils.isEmpty(timeNoteCO.getResult())) {
                weekTimeDO.setResult(timeNoteCO.getResult());
            }
            weekTimeDO.setGmtModified(LocalDateTime.now());
            weekTimeMapper.updateByPrimaryKeySelective(weekTimeDO);
        } else if (Constant.MONTH_TYPE.equals(type)) {
            MonthTimeDO monthTimeDO = monthTimeMapper.selectByPrimaryKey(id);
            if (!StringUtils.isEmpty(timeNoteCO.getBelongToScope())) {
                monthTimeDO.setBelongToScope(timeNoteCO.getBelongToScope());
            }
            if (!StringUtils.isEmpty(timeNoteCO.getProblem())) {
                monthTimeDO.setProblem(timeNoteCO.getProblem());
            }
            if (!StringUtils.isEmpty(timeNoteCO.getResult())) {
                monthTimeDO.setResult(timeNoteCO.getResult());
            }
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
