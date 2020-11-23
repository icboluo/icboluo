package com.icboluo.designpattern.principle.singleresponsibility1;

/**
 * 单一职责原则
 *
 * @author icboluo
 * @date 2020-09-01 17:13
 */
 class Demo {
    public static void main(String[] args) {
//        方式一：违反单一原则
        Vehicle vehicle = new Vehicle();
        vehicle.run("汽车");
        vehicle.run("船");
        vehicle.run("飞机");
//          方式二：把类分解，并且客户端（test）要修改，改动很大
        new RoadVehicle().run("汽车");
        new WaterVehicle().run("船");
        new AirVehicle().run("飞机");
//        方式三：把不同的方法写在同一个类中（具体实现略）
//        这种方法没有对类进行分解，只是增加了方法，改动较小，在方法级别满足单一原则（类级别不满足）

    }
}
