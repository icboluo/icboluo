package com.icboluo.object.viewobject;

import lombok.Data;

import java.util.List;

/**
 * @author icboluo
 */
@Data
public class SheetVO {
    /**
     * 表名
     */
    private String sheetName;
    /**
     * 行数据
     */
    private List<RowVO> list;
}
