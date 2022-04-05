package com.icboluo.designpattern.create.build;

/**
 * @author icboluo
 * @since 2020/11/7 21:05
 */
public abstract class HouseBuilder {
    /**
     * 受保护只能子类使用，建议用protected
     */
    protected House house = new House();

    public abstract void buildBasic();

    public abstract void buildWalls();

    public abstract void buildRoofed();

    public House buildHouse() {
        return house;
    }
}
