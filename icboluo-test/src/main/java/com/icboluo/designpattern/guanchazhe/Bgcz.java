package com.icboluo.designpattern.guanchazhe;

import com.icboluo.common.print.PrintInterface;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author icboluo
 * @date 2020-08-04 11:13
 */
@NoArgsConstructor
class Bgcz {
    List<PrintInterface> list = new ArrayList<>();


    public void addPrint(PrintInterface printInterface) {
        list.add(printInterface);
    }

    public void notifyPerson() {
        for (PrintInterface printInterface : list) {
            printInterface.print("被观察者要发送信息了");
        }
    }
}
