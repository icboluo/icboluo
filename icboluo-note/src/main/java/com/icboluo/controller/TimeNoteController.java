package com.icboluo.controller;

import com.icboluo.annotation.ResponseResult;
import com.icboluo.enumerate.ReEnum;
import com.icboluo.mapper.TimeNoteMapper;
import com.icboluo.object.clientobject.DDD;
import com.icboluo.object.clientobject.TimeNoteCO;
import com.icboluo.object.dataobject.TimeNoteDO;
import com.icboluo.object.query.TimeNoteQuery;
import com.icboluo.object.viewobject.FiledResultVO;
import com.icboluo.object.viewobject.NoteAllVO;
import com.icboluo.object.viewobject.NoteVO;
import com.icboluo.service.NoteService;
import com.icboluo.util.BeanHelper;
import com.icboluo.util.response.R;
import com.icboluo.util.response.Response;
import com.icboluo.util.validate.TimeNoteValidate;
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
@RequestMapping("/timeNote")
@Api(tags = "笔记本")
@ResponseResult
public class TimeNoteController {
    @Resource
    private TimeNoteMapper timeNoteMapper;
    @Resource
    private NoteService noteService;
    @Resource
    private TimeNoteValidate timeNoteValidate;


    @GetMapping("/init")
    @ApiOperation(value = "初始化")
    public Response init(TimeNoteQuery query) {
        List<NoteVO> list = noteService.selectList(query);
        return R.correct(BeanHelper.fakePage(list, query));
    }

    @GetMapping("/selectAmount")
    @ApiOperation(value = "查询问题剩余量")
    public Response selectTimeNoteAmount(TimeNoteQuery query) {
        NoteAllVO noteVO = noteService.selectOne(query);
        int timeNoteAmount = noteVO.getTimeNoteAmount();
        int weekTimeAmount = noteVO.getWeekTimeAmount();
        int monthTimeAmount = noteVO.getMonthTimeAmount();
        int totalTimeAmount = timeNoteAmount + weekTimeAmount + monthTimeAmount;
        Map<String, Integer> map = new HashMap<>(4);
        map.put("timeNoteAmount", timeNoteAmount);
        map.put("weekTimeAmount", weekTimeAmount);
        map.put("monthTimeAmount", monthTimeAmount);
        map.put("totalTimeAmount", totalTimeAmount);
        return R.correct(map);
    }

    @PostMapping("/add")
    @ApiOperation(value = "增加")
    public Response add(TimeNoteCO client) {
        TimeNoteDO timeNoteDO = BeanHelper.copyProperties(client, TimeNoteDO.class);
        int i = timeNoteMapper.insertSelective(timeNoteDO);
        return i == 1 ? R.correct(ReEnum.ADD_SUCCESSFUL)
                : R.error(ReEnum.ADD_ERROR);
    }

    @PostMapping("/update")
    @ApiOperation(value = "更新数据")
    public void update(TimeNoteCO client) {
        timeNoteValidate.validate(client);
        TimeNoteQuery timeNoteQuery = new TimeNoteQuery();
        Map<String, String> map = noteService.selectIdAndType(timeNoteQuery);
        noteService.update(client, map);
    }

    @GetMapping("/onlyUpdateTime")
    @ApiOperation(value = "只更新时间")
    public void onlyUpdateTime(DDD dd) {
        noteService.onlyUpdateTime(dd);
    }

    @GetMapping("/updateFinishTime")
    @ApiOperation(value = "更新为完成了一次")
    public void update(DDD dd) {
        noteService.updateFinishTime(dd);
    }

    @GetMapping("/updateNotFinishTime")
    @ApiOperation(value = "更新为没有完成一次")
    public void updateNotFinishTime(DDD dd) {
        noteService.updateNotFinishTime(dd);
    }

    @GetMapping("/toOnlyRead")
    @ApiOperation(value = "仅仅更新时间")
    public void toOnlyRead(DDD dd) {
        noteService.toOnlyRead(dd);
    }

    @GetMapping("/selectByFiled")
    @ApiOperation(value = "根据字段查询")
    public List<FiledResultVO> select(@RequestParam("filed") String file) {
        return noteService.select(file);
    }

    @GetMapping("/updateProblem")
    public void updateProblem() {

    }
}
