package com.icboluo.util.listenter;

import com.alibaba.excel.context.AnalysisContext;
import com.icboluo.common.ExcelTitleMap;
import com.icboluo.util.ExcelHelp;
import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author icboluo
 * @since 2023-07-05 18:57
 */
public class ValidHeadExcelListener<T extends ExcelTitleMap> extends ExcelListener<T> {
    public ValidHeadExcelListener(Class<T> clazz) {
        super(clazz);
    }

    public ValidHeadExcelListener(Class<T> clazz, Integer head) {
        super(clazz, head);
    }

    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {

    }

    @SneakyThrows
    private List<String> validHead(Map<Integer, String> headMap) {
        T head = getClazz().getConstructor().newInstance();
        Map<String, String> titleMap = head.titleMap();
        List<String> msg = new ArrayList<>();

        // 校验基于java端，只要匹配java校验规则即可，Excel中多了的数据不处理
        for (Map.Entry<String, String> entry : titleMap.entrySet()) {
            String lineStr = entry.getKey();
            String config = entry.getValue();
            int line = ExcelHelp.titleToNumber(lineStr);
            String excel = headMap.get(line);
            if (excel == null || !excel.contains(config)) {
                msg.add(lineStr);
            }
        }
        return msg;
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        // Not need impl Method
    }
}
