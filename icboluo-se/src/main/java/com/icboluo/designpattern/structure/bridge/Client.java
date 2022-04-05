package com.icboluo.designpattern.structure.bridge;

/**
 * @author icboluo
 * @since 2020/12/10 22:31
 */
public class Client {
    public static void main(String[] args) {
        Phone phone1 = new FoldedPhone(new XiaoMi());
        phone1.open();
        phone1.call();
        phone1.close();
    }
}
