package com.icboluo.shejimoshi.build;

/**
 * @author icboluo
 * @date 2020/10/27 19:58
 */
public class Client {
    public static void main(String[] args) {
        CommonHouseBuilder commonHouseBuilder = new CommonHouseBuilder();
        HouseDirect houseDirect = new HouseDirect(commonHouseBuilder);
        House house = houseDirect.constructHouse();
        HighHouse highHouse = new HighHouse();
        houseDirect.setHb(highHouse);
        house = houseDirect.constructHouse();
        System.out.println(house);
    }
}
