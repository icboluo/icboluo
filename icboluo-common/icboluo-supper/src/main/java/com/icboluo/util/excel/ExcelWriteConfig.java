package com.icboluo.util.excel;

import com.alibaba.excel.write.handler.WriteHandler;
import com.icboluo.util.BeanUtil;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author icboluo
 * @since 2024-04-02 0:09
 */
@Data
public class ExcelWriteConfig {

    private String fileName;

    private String sheetName;

    private List<WriteHandler> writeHandlerList = new ArrayList<>();

    public ExcelWriteConfig(String fileName) {
        this.fileName = BeanUtil.removeSuffix(fileName, ".xlsx");
        this.sheetName = this.fileName;
    }

    public ExcelWriteConfig(String fileName, String sheetName) {
        this.fileName = BeanUtil.removeSuffix(fileName, ".xlsx");
        this.sheetName = sheetName;
    }
}
