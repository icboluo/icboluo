package com.icboluo.util.excel;

import com.alibaba.excel.write.handler.WriteHandler;
import lombok.Data;

import java.util.List;

/**
 * @author icboluo
 * @since 2024-04-02 0:09
 */
@Data
public class EasyExcelWriteConfig {

    private String fileName;

    private List<WriteHandler> writeHandlerList;

    public EasyExcelWriteConfig(String fileName) {
        this.fileName = fileName;
    }
}
