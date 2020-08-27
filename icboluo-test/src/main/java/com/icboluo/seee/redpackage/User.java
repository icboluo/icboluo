package com.icboluo.seee.redpackage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author icboluo
 * @date 2020/6/15 10:48
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    /**
     * 用户名
     */
    private String username;

    /**
     * 余额
     */
    private double leftMoney;

    public void show() {
        System.out.println("用户名:"+ username +" , 余额为:" + leftMoney + "元");
    }
}
