package com.icboluo.designpattern.a2_create.build;

/**
 * @author icboluo
 * @since 2020/11/7 21:05
 */
public class CommonHouseBuilder extends HouseBuilder {
    @Override
    public void buildBasic() {
        super.house.setBasic("10 m");
    }

    @Override
    public void buildWalls() {
        super.house.setWalls("10 cm");
    }

    @Override
    public void buildRoofed() {
        super.house.setRoofed("1 cm");
    }
}
