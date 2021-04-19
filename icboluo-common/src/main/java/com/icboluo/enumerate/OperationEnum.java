package com.icboluo.enumerate;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author icboluo
 */

@AllArgsConstructor
@Getter
public enum OperationEnum implements EnumInter {
    /**
     * 自由态
     */
    SAVE("save", "保存操作", 1),
    COMMIT("commit", "提交操作", 1),
    CREATE("create", "创建操作", 1),
    ;
    /**
     * 英文描述
     */
    private final String en;
    /**
     * 中文描述
     */
    private final String zh;
    /**
     * 状态值
     */
    private final Integer id;
}
