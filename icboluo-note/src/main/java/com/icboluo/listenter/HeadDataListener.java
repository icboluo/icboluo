package com.icboluo.listenter;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.read.metadata.holder.ReadRowHolder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 直接用map接收数据
 *
 * @author icboluo
 */
@Slf4j
public class HeadDataListener extends AnalysisEventListener<Map<Integer, String>> {
@Getter
    private List<String> head = new ArrayList<>();

    @Override
    public void invoke(Map<Integer, String> data, AnalysisContext context) {
        ReadRowHolder readRowHolder = context.readRowHolder();
        Integer rowIndex = readRowHolder.getRowIndex();
        if (rowIndex == 0) {
            head = new ArrayList<>(data.values());
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
    }
}
