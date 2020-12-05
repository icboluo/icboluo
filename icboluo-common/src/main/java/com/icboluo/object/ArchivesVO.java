package com.icboluo.object;

/**
 * 档案视图对象
 *
 * @author icboluo
 * @date 2020/12/3 21:40
 */
public class ArchivesVO {

    /**
     * <p/>取db中id
     * <p/>如果不是和db交互，取唯一不可读字段
     */
    private String id;

    /**
     * <p/>取db中可读编码
     * <p/>如果不是和db交互，取唯一可读字段
     */
    private String code;

    /**
     * 名称
     */
    private String name;

}
