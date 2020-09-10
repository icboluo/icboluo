package com.icboluo.util;

import com.alibaba.fastjson.JSONObject;
import com.icboluo.common.ReEnum;

import java.util.Collection;
import java.util.List;

/**
 * 这个工具类的目的是将值写到Response这个类的对象中；
 * <p>为了统一前端的取参格式，本类约定：
 * <p>1.只要返回值是消息、状态码，必须放在code/message里面，不准放在data里面；
 * <p>2.只要返回值是一个对象，那就必须放在data.object里面，不准直接放到data里面；
 * <p>3.只要返回值是一个list数据，那就必须放在data.list里面，不准直接放到data里面；
 * <p>4.本类只提供将数据放到Response里面，不准增加额外的属性（例如分页数据的构建），
 * 也就是说，本类所写的返回值中，绝对不会包含传入参数以外的数据（枚举项、异常除外）；
 *
 * @author icboluo
 */
@SuppressWarnings("unused,rawtypes")
public class R {

    public static Response correct() {
        return new SingleResponse<>(ReEnum.OPERATION_SUCCESSFUL);
    }

    public static Response correct(ReEnum reEnum) {
        return new SingleResponse<>(reEnum);
    }

    /**
     * 本方法只提供code和message的构建，不提供data数据的构建
     *
     * @param message message
     * @return 响应类
     */
    public static Response correct(String message) {
        return new SingleResponse<>(ReEnum.OPERATION_SUCCESSFUL.getCode(), message);
    }

    public static Response correct(String data, ReEnum reEnum) {
        return new SingleResponse<>(data, reEnum);
    }

    public static <T> Response correct(T t) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("object", t);
        return new SingleResponse<>(jsonObject, ReEnum.OPERATION_SUCCESSFUL);
    }

    public static <T> Response correct(List<T> list) {
        return new SingleResponse<>(getJsonObject(list), ReEnum.OPERATION_SUCCESSFUL);
    }

    public static Response correct(List... lists) {
        JSONObject jsonObject = getJsonObject(lists);
        return new SingleResponse<>(jsonObject, ReEnum.OPERATION_SUCCESSFUL);
    }

    public static <T> Response correct(List<T> list, ReEnum reEnum) {
        return new SingleResponse<>(getJsonObject(list), reEnum);
    }

    public static <T, D> Response correct(T t, List<D> list) {
        return correct(t, list, ReEnum.OPERATION_SUCCESSFUL);
    }

    public static <T, D> Response correct(T t, List<D> list, ReEnum reEnum) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("object", t);
        jsonObject.put("list", list);
        return new SingleResponse<>(jsonObject, reEnum);
    }

    public static Response error() {
        return new SingleResponse<>(ReEnum.ERROR);
    }

    public static Response error(String errMsg) {
        return new SingleResponse<>(ReEnum.ERROR.getCode(), errMsg);
    }

    public static Response error(int code, String errMsg) {
        return new SingleResponse<>(code, errMsg);
    }

    public static Response error(int code, Exception e) {
        return new SingleResponse<>(code, e.getMessage());
    }

    public static Response error(ReEnum errEnum) {
        return new SingleResponse<>(errEnum);
    }

    public static Response error(Exception e) {
        return new SingleResponse<>(ReEnum.ERROR.getCode(), e.getMessage());
    }

    /**
     * 将参数放到JSONObject中
     *
     * @param t 参数
     * @return json对象
     */
    private static <T> JSONObject getJsonObject(T t) {
        JSONObject jo = new JSONObject();
        if (t instanceof Collection) {
            jo.put("list", t);
        } else if (t.getClass().isArray()) {
            Collection[] lists = (Collection[]) t;
            if (lists.length > 0) {
                for (int i = 0; i < lists.length; i++) {
                    jo.put("list" + (i + 1), lists[i]);
                }
            }
        }
        return jo;
    }

}
