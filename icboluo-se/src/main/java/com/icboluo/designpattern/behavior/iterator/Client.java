package com.icboluo.designpattern.behavior.iterator;

import java.util.ArrayList;
import java.util.List;

/**
 * @author icboluo
 * @since 2022-04-06 0:14
 */
class Client {
    public static void main(String[] args) {
        List<College> collegeList = new ArrayList<>();

        ComputerCollege computerCollege = new ComputerCollege();
        InfoCollege infoCollege = new InfoCollege();

        collegeList.add(computerCollege);
        collegeList.add(infoCollege);

        OutPutImpl outPut = new OutPutImpl(collegeList);
        outPut.printCollege();
    }
}
