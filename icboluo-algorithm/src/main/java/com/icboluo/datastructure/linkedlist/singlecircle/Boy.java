package com.icboluo.datastructure.linkedlist.singlecircle;

import lombok.Data;

/**
 * @author icboluo
 */
@Data
public class Boy {
    private int no;
    private Boy next;

    public Boy(int no) {
        this.no = no;
    }
}
