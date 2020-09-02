package com.icboluo.designpattern.factory;

import com.icboluo.common.print.Print01;
import com.icboluo.common.print.Print02;
import com.icboluo.common.print.PrintInterface;

public class Factory {

    public PrintInterface print(int i) {
        if (i ==1) {
            return new Print01();
        }else{
            return new Print02();
        }
    }
}
