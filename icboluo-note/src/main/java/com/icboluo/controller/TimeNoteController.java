package com.icboluo.controller;

import com.icboluo.entity.note.TimeNoteDO;
import com.icboluo.enumerate.ReEnum;
import com.icboluo.mapper.TimeNoteMapper;
import com.icboluo.object.client.TimeNoteCO;
import com.icboluo.object.client.TimeUpdateCO;
import com.icboluo.object.query.TimeNoteQuery;
import com.icboluo.object.view.FiledResultVO;
import com.icboluo.object.view.NoteVO;
import com.icboluo.service.NoteService;
import com.icboluo.util.BeanUtil;
import com.icboluo.util.IcBoLuoException;
import com.icboluo.util.response.R;
import com.icboluo.util.response.Response;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author lp
 */
@RestController
@RequestMapping("timeNote")
public class TimeNoteController {
    @Resource
    private TimeNoteMapper timeNoteMapper;
    @Resource
    private NoteService noteService;


    @GetMapping("init")
    public Response init(TimeNoteQuery query) {
        List<NoteVO> list = noteService.selectList(query);
        return R.correct(BeanUtil.fakePage(list, query));
    }

    @GetMapping("selectAmount")
    public Map<String, Integer> selectTimeNoteAmount() {
        return noteService.selectAmount();
    }

    @GetMapping("add")
    public Response add(TimeNoteCO client) {
        TimeNoteDO timeNoteDO = BeanUtil.copyProperties(client, TimeNoteDO.class);
        int i = timeNoteMapper.insertSelective(timeNoteDO);
        return i == 1 ? R.correct(ReEnum.ADD_SUCCESSFUL)
                : R.error(ReEnum.ADD_ERROR);
    }

    @GetMapping("update")
    public void update(TimeNoteCO client, String type) {
        validate(client);
        int id = client.getId();
        noteService.update(client, id, type);
    }

    public void validate(TimeNoteCO obj) {
        if (BeanUtil.allIsNull(obj.getProblem(), obj.getResult(), obj.getBelongToScope())) {
            throw new IcBoLuoException();
        }
    }

    @GetMapping("onlyUpdateTime")
    public void onlyUpdateTime(TimeUpdateCO client) {
        noteService.onlyUpdateTime(client);
    }

    @GetMapping("updateFinishTime")
    public void update(TimeUpdateCO client) {
        noteService.updateFinishTime(client);
    }

    @GetMapping("updateNotFinishTime")
    public void updateNotFinishTime(TimeUpdateCO client) {
        noteService.updateNotFinishTime(client);
    }

    @GetMapping("toOnlyRead")
    public void toOnlyRead(TimeUpdateCO client) {
        noteService.toOnlyRead(client);
    }

    @GetMapping("selectByFiled")
    public List<FiledResultVO> select(@RequestParam("filed") String file) {
        return noteService.select(file);
    }

    @GetMapping("updateProblem")
    public void updateProblem() {

    }
}
