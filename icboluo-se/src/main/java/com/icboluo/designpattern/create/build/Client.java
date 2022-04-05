package com.icboluo.designpattern.create.build;

/**
 * @author icboluo
 * @since 2020/10/27 19:58
 */
public class Client {
    public static void main(String[] args) {
        CommonHouseBuilder commonHouseBuilder = new CommonHouseBuilder();
        HouseDirect houseDirect = new HouseDirect(commonHouseBuilder);
        House house = houseDirect.constructHouse();
        HighHouseBuilder highHouseBuilder = new HighHouseBuilder();
        houseDirect.setHb(highHouseBuilder);
        house = houseDirect.constructHouse();
        System.out.println(house);
    }
}
