package com.icboluo.designpattern.behavior.command;

/**
 * 没有任何命令，空执行，用于初始化一个按钮，当调用空命令时，对象什么都不做即可
 * 可以省掉对空判断
 *
 * @author icboluo
 * @since 2020/11/15 21:27
 */
public class NoCommand implements Command {

    LightReceiver lightReceiver;

    @Override
    public void execute() {
        lightReceiver.off();
    }

    @Override
    public void undo() {
        lightReceiver.on();
    }
}
