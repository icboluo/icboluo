package com.icboluo.enumerate;

import lombok.AllArgsConstructor;
import lombok.Getter;


/**
 * 异常枚举项
 *
 * @author icboluo
 */

@Getter
@AllArgsConstructor
public enum ExceptionEnum {
    // 枚举项必须写在类的最前面
    /**
     * 价格不能为空
     */
    PRICE_CANNOT_BE_NULL(400, "价格不能为空"),
    INVALID_FILE_TYPE(400, "无效的文件类型！"),
    INVALID_PARAM_ERROR(400, "无效的请求参数！"),
    INVALID_PHONE_NUMBER(400, "无效的手机号码"),
    INVALID_VERIFY_CODE(400, "验证码错误！"),
    INVALID_USERNAME_PASSWORD(400, "无效的用户名和密码！"),
    INVALID_SERVER_ID_SECRET(400, "无效的服务id和密钥！"),
    INVALID_NOTIFY_PARAM(400, "回调参数有误！"),
    INVALID_NOTIFY_SIGN(400, "回调签名有误！"),

    CATEGORY_NOT_FOUND(404, "商品分类不存在！"),
    BRAND_NOT_FOUND(404, "品牌不存在！"),
    SPEC_NOT_FOUND(404, "规格不存在！"),
    GOODS_NOT_FOUND(404, "商品不存在！"),
    CARTS_NOT_FOUND(404, "购物车不存在！"),
    APPLICATION_NOT_FOUND(404, "应用不存在！"),
    ORDER_NOT_FOUND(404, "订单不存在！"),
    ORDER_DETAIL_NOT_FOUND(404, "订单数据不存在！"),

    DATA_TRANSFER_ERROR(500, "数据转换异常！"),
    INSERT_OPERATION_FAIL(500, "新增操作失败！"),
    UPDATE_OPERATION_FAIL(500, "更新操作失败！"),
    DELETE_OPERATION_FAIL(500, "删除操作失败！"),
    FILE_UPLOAD_ERROR(500, "文件上传失败！"),
    DIRECTORY_WRITER_ERROR(500, "目录写入失败！"),
    FILE_WRITER_ERROR(500, "文件写入失败！"),
    SEND_MESSAGE_ERROR(500, "短信发送失败！"),
    INVALID_ORDER_STATUS(500, "订单状态不正确！"),
    STOCK_NOT_ENOUGH_ERROR(500, "库存不足！"),

    UNAUTHORIZED(401, "登录失效或未登录！"),
    ENUM_DEFINED_ERROR(550, "枚举定义异常"),
    STATUS_VALUE_ERROR(560, "状态值异常"),
    STATUS_VALUE_NOT_FOUND(561, "状态值不存在"),
    SYSTEM_NOT_SUPPORT_EXCEPTION(601, "系统不支持异常"),

    CUR_DATA_NOT_PERMISSION_THIS_OPERATION(500, "当前数据不允许次操作"),
    NO_PERMISSION(500, "没有权限"),
    NO_DATA(500, "没有数据"),
    ERROR(500, "预料之外的异常"),
    PARAM_ERROR(500, "参数不合法"),
    ;
    private final int status;

    private final String message;
}
