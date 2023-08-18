package com.icboluo.object.view;

import com.icboluo.annotation.ExcelExport;
import com.icboluo.entity.Student;
import com.icboluo.util.BeanUtil;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author icboluo
 * @since 2023-08-01 0:38
 */
@Data
@NoArgsConstructor
public class StudentVO {

    @ExcelExport(zh = "序号", columnNumber = "d")
    private Integer id;
    @ExcelExport(zh = "编码", columnNumber = "b")
    private Integer code;
    @ExcelExport(zh = "姓名", columnNumber = "e")
    private String name;
    @ExcelExport(zh = "年龄", columnNumber = "g")
    private Integer age;

    public StudentVO(Student student) {
        BeanUtil.copyProperties(student, this);
    }
}
