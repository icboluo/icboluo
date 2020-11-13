package com.icboluo.enumerate;

import lombok.AllArgsConstructor;

/**
 * @author icboluo
 */

@AllArgsConstructor
public enum OperationEnum {
    /**
     * 自由态
     */
    SAVE("save", "保存操作", 1),
    COMMIT("commit", "提交操作", 1),
    ;
    /**
     * 英文描述
     */
    private final String english;
    /**
     * 中文描述
     */
    private final String chinese;
    /**
     * 状态值
     */
    private final Integer status;
}
