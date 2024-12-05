package com.icboluo.controller;

import com.icboluo.entity.NoteTimeNote;
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
import org.springframework.web.bind.annotation.*;

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


    @PostMapping("init")
    public Response init(@RequestBody TimeNoteQuery query) {
        List<NoteVO> list = noteService.selectList(query);
        return R.correct(BeanUtil.fakePage(list, query));
    }

    @PostMapping("selectAmount")
    public Map<String, Integer> selectTimeNoteAmount() {
        return noteService.selectAmount();
    }

    @PostMapping("add")
    public Response add(@RequestBody TimeNoteCO client) {
        NoteTimeNote noteTimeNote = BeanUtil.copyProperties(client, NoteTimeNote.class);
        int i = timeNoteMapper.insertSelective(noteTimeNote);
        return i == 1 ? R.correct(ReEnum.ADD_SUCCESSFUL)
                : R.error(ReEnum.ADD_ERROR);
    }

    @PostMapping("update")
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

    @PostMapping("onlyUpdateTime")
    public void onlyUpdateTime(@RequestBody TimeUpdateCO client) {
        noteService.onlyUpdateTime(client);
    }

    @PostMapping("updateFinishTime")
    public void update(@RequestBody TimeUpdateCO client) {
        noteService.updateFinishTime(client);
    }

    @PostMapping("updateNotFinishTime")
    public void updateNotFinishTime(@RequestBody TimeUpdateCO client) {
        noteService.updateNotFinishTime(client);
    }

    @PostMapping("toOnlyRead")
    public void toOnlyRead(@RequestBody TimeUpdateCO client) {
        noteService.toOnlyRead(client);
    }

    @PostMapping("selectByFiled")
    public List<FiledResultVO> select(@RequestBody String file) {
        return noteService.select(file);
    }

    @PostMapping("updateProblem")
    public void updateProblem() {

    }

    public static void main(String[] args) {
        double dayOfMonth = 21.75;
        int other = 0;
        int base = 10000;
        int total = other+base;
        double oneDayMoney = total / dayOfMonth;
        System.out.println("ont day money: "+oneDayMoney);
        // 加班
        int jbDay = 2;
        double oneDayMonth2 = total / (dayOfMonth + jbDay);
        System.out.println("jb money: "+oneDayMonth2);
        // 加班工资
        int jdMoney = 0;
        double oneDayMonth3 = (total+jdMoney) / (dayOfMonth + jbDay);
        System.out.println("jb money: "+oneDayMonth3);
    }
}
