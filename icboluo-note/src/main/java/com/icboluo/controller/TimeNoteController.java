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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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

    public static void main(String[] args) {
        int base = 10000;
        int performance = 4000;
        int normal = base + performance;

        double jb = base / 21.75 * 2;
        System.out.println("jb : " + jb * 0.8 + ", ...");

        extracted(normal, jb * 0, 0);

        extracted(normal, jb * 1, 1);

        extracted(normal, jb * 2, 2);

        extracted(normal, jb * 3, 3);

        extracted(normal, jb * 4, 4);

        extracted(normal, jb * 5, 5);
    }

    private static void extracted(int normal, double jb, int x) {
        double total = normal + jb;
        double oneDayMonth = total / (21.75 + x);
        int other = 2093;
        double taxableIncome = total - 5000 - other;
        double tax = tax(taxableIncome * 12) / 12;
        System.out.printf("total money: %.2f, one day money: %.2f, actual money: %.2f%n", total, oneDayMonth, total - tax - other);
    }

    private static double tax(double taxableIncome) {
        if (taxableIncome <= 36000) {
            return taxableIncome * 0.03 - 0;
        } else if (taxableIncome <= 144000) {
            return taxableIncome * 0.10 - 2520;
        } else if (taxableIncome <= 300000) {
            return taxableIncome * 0.20 - 16920;
        } else if (taxableIncome <= 420000) {
            return taxableIncome * 0.25 - 31920;
        }
        return -1;
    }
}
