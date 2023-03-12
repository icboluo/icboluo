package com.icboluo.designpattern.a2_create.build;

import lombok.AllArgsConstructor;
import lombok.Setter;

/**
 * 指挥者
 *
 * @author icboluo
 * @since 2020/11/7 21:11
 */
@AllArgsConstructor
public class HouseDirect {

    @Setter
    HouseBuilder hb;

    /**
     * 这里可以对对象的构建进行排序：比如先打地基，在砌墙，在打屋顶
     *
     * @return
     */
    public House constructHouse() {
        hb.buildBasic();
        hb.buildWalls();
        hb.buildRoofed();
        return hb.buildHouse();
    }
}
