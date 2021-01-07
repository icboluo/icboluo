package com.icboluo.designpattern.principle.inversion3.improve;

/**
 * @author icboluo
 * @date 2020-09-02 16:05
 */
 class Email implements MyReceiver{
    @Override
    public String getInfo() {
        return "电子邮件信息： hello email";
    }
}
