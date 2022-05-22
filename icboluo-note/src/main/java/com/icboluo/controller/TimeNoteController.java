package com.icboluo.controller;

import com.icboluo.annotation.ResponseResult;
import com.icboluo.entity.note.TimeNoteDO;
import com.icboluo.enumerate.ReEnum;
import com.icboluo.mapper.TimeNoteMapper;
import com.icboluo.object.client.TimeNoteCO;
import com.icboluo.object.client.TimeUpdateCO;
import com.icboluo.object.query.TimeNoteQuery;
import com.icboluo.object.view.FiledResultVO;
import com.icboluo.object.view.NoteVO;
import com.icboluo.service.NoteService;
import com.icboluo.util.BeanHelper;
import com.icboluo.util.response.R;
import com.icboluo.util.response.Response;
import com.icboluo.util.validate.TimeNoteValidate;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
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
    public Map<String, Integer> selectTimeNoteAmount() {
        return noteService.selectAmount();
    }

    @GetMapping("/add")
    @ApiOperation(value = "增加")
    public Response add(TimeNoteCO client) {
        TimeNoteDO timeNoteDO = BeanHelper.copyProperties(client, TimeNoteDO.class);
        int i = timeNoteMapper.insertSelective(timeNoteDO);
        return i == 1 ? R.correct(ReEnum.ADD_SUCCESSFUL)
                : R.error(ReEnum.ADD_ERROR);
    }

    @GetMapping("/update")
    @ApiOperation(value = "更新数据")
    public void update(TimeNoteCO client, String type) {
        timeNoteValidate.validate(client);
        int id = client.getId();
        noteService.update(client, id, type);
    }

    @GetMapping("/onlyUpdateTime")
    @ApiOperation(value = "只更新时间")
    public void onlyUpdateTime(TimeUpdateCO client) {
        noteService.onlyUpdateTime(client);
    }

    @GetMapping("/updateFinishTime")
    @ApiOperation(value = "更新为完成了一次")
    public void update(TimeUpdateCO client) {
        noteService.updateFinishTime(client);
    }

    @GetMapping("/updateNotFinishTime")
    @ApiOperation(value = "更新为没有完成一次")
    public void updateNotFinishTime(TimeUpdateCO client) {
        noteService.updateNotFinishTime(client);
    }

    @GetMapping("/toOnlyRead")
    @ApiOperation(value = "仅仅更新时间")
    public void toOnlyRead(TimeUpdateCO client) {
        noteService.toOnlyRead(client);
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
