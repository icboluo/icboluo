package com.icboluo.controller;

import com.icboluo.enumerate.ReEnum;
import com.icboluo.mapper.TimeNoteMapper;
import com.icboluo.object.clientobject.TimeNoteCO;
import com.icboluo.object.dataobject.TimeNoteDO;
import com.icboluo.object.query.TimeNoteQuery;
import com.icboluo.object.viewobject.FiledResultVO;
import com.icboluo.object.viewobject.NoteVO;
import com.icboluo.service.NoteService;
import com.icboluo.util.BeanHelper;
import com.icboluo.util.R;
import com.icboluo.util.Response;
import com.icboluo.validate.TimeNoteValidate;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lp
 */
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@Api(tags = "笔记本")
public class TimeNoteController {
    @Resource
    private TimeNoteMapper timeNoteMapper;
    @Resource
    private NoteService noteService;
    @Resource
    private TimeNoteValidate timeNoteValidate;

    @GetMapping("/selectProblem")
    @ApiOperation(value = "查询第一个问题")
    public Response selectProblem(TimeNoteQuery query) {
        NoteVO noteVO = noteService.selectOne(query);
        return R.correct(noteVO);
    }

    @GetMapping("/selectAmount")
    @ApiOperation(value = "查询问题剩余量")
    public Response selectTimeNoteAmount(TimeNoteQuery query) {
        NoteVO noteVO = noteService.selectOne(query);
        int timeNoteAmount = noteVO.getTimeNoteAmount();
        int weekTimeAmount = noteVO.getWeekTimeAmount();
        int monthTimeAmount = noteVO.getMonthTimeAmount();
        int totalTimeAmount = timeNoteAmount + weekTimeAmount + monthTimeAmount;
        HashMap<String, Integer> map = new HashMap<>(4);
        map.put("timeNoteAmount", timeNoteAmount);
        map.put("weekTimeAmount", weekTimeAmount);
        map.put("monthTimeAmount", monthTimeAmount);
        map.put("totalTimeAmount", totalTimeAmount);
        return R.correct(map);
    }

    @PostMapping("/add")
    @ApiOperation(value = "增加")
    public Response add(TimeNoteCO timeNoteCO) {
        TimeNoteDO timeNoteDO = BeanHelper.copyProperties(timeNoteCO, TimeNoteDO.class);
        int i = timeNoteMapper.insertSelective(timeNoteDO);
        return i == 1 ? R.correct(ReEnum.ADD_SUCCESSFUL)
                : R.error(ReEnum.ADD_ERROR);
    }

    @PostMapping("/update")
    @ApiOperation(value = "更新数据")
    public Response update(TimeNoteCO timeNoteCO) {
        timeNoteValidate.validate(timeNoteCO);
        TimeNoteQuery timeNoteQuery = new TimeNoteQuery();
        Map<String, String> map = noteService.selectIdAndType(timeNoteQuery);
        noteService.update(timeNoteCO, map);
        return R.correct(ReEnum.UPDATE_SUCCESSFUL);
    }

    @GetMapping("/onlyUpdateTime")
    @ApiOperation(value = "只更新时间")
    public Response onlyUpdateTime() {
        TimeNoteQuery timeNoteQuery = new TimeNoteQuery();
        Map<String, String> map = noteService.selectIdAndType(timeNoteQuery);
        noteService.onlyUpdateTime(map);
        return R.correct(ReEnum.UPDATE_SUCCESSFUL);
    }

    @GetMapping("/updateFinishTime")
    @ApiOperation(value = "更新为完成了一次")
    public Response updateFinishTime() {
        TimeNoteQuery timeNoteQuery = new TimeNoteQuery();
        Map<String, String> map = noteService.selectIdAndType(timeNoteQuery);
        noteService.updateFinishTime(map);
        return R.correct(ReEnum.UPDATE_SUCCESSFUL);
    }

    @GetMapping("/updateNotFinishTime")
    @ApiOperation(value = "更新为没有完成一次")
    public Response updateNotFinishTime() {
        TimeNoteQuery timeNoteQuery = new TimeNoteQuery();
        Map<String, String> map = noteService.selectIdAndType(timeNoteQuery);
        noteService.updateNotFinishTime(map);
        return R.correct(ReEnum.UPDATE_SUCCESSFUL);
    }

    @GetMapping("/toOnlyRead")
    @ApiOperation(value = "仅仅更新时间")
    public Response toOnlyRead() {
        noteService.toOnlyRead();
        return R.correct(ReEnum.UPDATE_SUCCESSFUL);
    }

    @GetMapping("/selectByFiled")
    @ApiOperation(value = "根据字段查询")
    public Response select(@RequestParam("filed") String file) {
        List<FiledResultVO> list = noteService.select(file);
        return R.correct(list);
    }
}
