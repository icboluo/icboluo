package com.icboluo.object.business;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author icboluo
 * @since 2020/11/20 22:15
 */
@Data
public class StudentBO {

    @ExcelProperty
    private String id;

    @ExcelProperty
    private String code;

    @ExcelProperty
    private String name;

    @ExcelProperty
    private Integer age;
}
