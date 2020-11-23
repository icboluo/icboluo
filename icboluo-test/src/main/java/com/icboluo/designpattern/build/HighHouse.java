package com.icboluo.designpattern.build;

/**
 * @author icboluo
 * @date 2020/11/7 21:10
 */
public class HighHouse extends HouseBuilder {
    @Override
    public void buildBasic() {
        super.house.setBasic("20 m");
    }

    @Override
    public void buildWalls() {
        super.house.setWalls("20 cm");
    }

    @Override
    public void buildRoofed() {
        super.house.setRoofed("2 cm");
    }
}
