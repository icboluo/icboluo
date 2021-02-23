package com.icboluo.object;

import lombok.Data;

/**
 * 物料的视图对象，比档案对象对了数量字段
 *
 * @author icboluo
 * @date 2020/12/3 21:39
 */
@Data
public class MaterialsVO {

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

    /**
     * 数量
     */
    private Integer quantity;
}
