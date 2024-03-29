package com.icboluo.designpattern.a2_create.build;

import lombok.Data;
import lombok.ToString;

/**
 * @author icboluo
 * @since 2020/11/7 21:04
 */
@Data
@ToString
public class House {
    /**
     * 地基
     */
    private String basic;
    /**
     * 砌墙
     */
    private String walls;
    /**
     * 屋顶
     */
    private String roofed;
}
