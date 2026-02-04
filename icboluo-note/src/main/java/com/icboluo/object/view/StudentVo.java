package com.icboluo.object.view;

import com.icboluo.annotation.Excel;
import com.icboluo.entity.Student;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

/**
 * @author icboluo
 * @since 2023-08-01 0:38
 */
@Data
@NoArgsConstructor
public class StudentVo {

    @Excel(zh = "序号", columnNumber = "d")
    private Integer id;
    @Excel(zh = "编码", columnNumber = "b")
    private Integer code;
    @Excel(zh = "姓名", columnNumber = "e")
    private String name;
    @Excel(zh = "年龄", columnNumber = "g")
    private Integer age;

    public StudentVo(Student student) {
        BeanUtils.copyProperties(student, this);
    }
}
