package com.icboluo.enumerate;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 返回值枚举项
 *
 * @author icboluo
 */

@Getter
@AllArgsConstructor
public enum ReEnum {
    /**
     * 操作成功
     */
    OPERATION_SUCCESSFUL(0, "操作成功"),
    /**
     * 新增成功
     */
    ADD_SUCCESSFUL(0, "新增成功"),
    DELETE_SUCCESSFUL(0, "删除成功"),
    UPDATE_SUCCESSFUL(0, "更新成功"),
    SELECT_SUCCESSFUL(0, "查询成功"),
    DATA_ECHO_SUCCESSFUL(0, "数据回显成功"),
    AUDIT_SUCCESSFUL(0, "审核成功"),
    ABANDON_SUCCESSFUL(0, "弃审成功"),
    REFER_SUCCESSFUL(0, "参照成功"),
    SUBMIT_SUCCESSFUL(0, "提交成功"),
    REFUSE_AUDIT_SUCCESSFUL(0, "拒审成功"),
    REVISE_SUCCESSFUL(0, "修订成功"),
    LOCK_SUCCESSFUL(0, "锁库成功"),
    ORDER_STATUS_UPDATE_SUCCESSFUL(0, "订单状态更新成功"),
    /**
     * 新增失败
     */
    ADD_ERROR(-1, "新增失败"),
    /**
     * 参数为空
     */
    NULL_PARAM(-1, "参数为空"),
    /**
     * 非法数据
     */
    ILLEGALITY_DATA(-1, "非法数据"),
    /**
     * 失败
     */
    ERROR(-1, "失败");

    private final Integer code;
    private final String message;

}
